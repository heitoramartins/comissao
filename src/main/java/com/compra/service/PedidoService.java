package com.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.compra.business.exception.PedidoNotCreateException;
import com.compra.business.exception.PedidoNotUpdateException;
import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.entity.enums.StatusDesconto;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.dao.PedidoDAO;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.status.pedido.NivelPedido;


@Component
public class PedidoService {
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BeanFactory bf;
	
	@Autowired
	private List<AcoesAposGerarPedido> acoes;
	
	@Transactional
	public List<Pedido> listPedidosById(Long id){
		List<Pedido> pedidos = pedidoDAO.listPedidosById(id);
		return pedidos;
	}
	
	@Transactional
	public Pedido findById(Long id){
		Pedido pedido = pedidoDAO.findById(id);
		return pedido;
	}
	
	@Transactional
	public Long salvarOrcamento(Pedido pedido){
		BigDecimal total = BigDecimal.ZERO;	
		BigDecimal totalCalculadoFreteMaisDesconto = BigDecimal.ZERO;	
		try {
			if(pedido.isNovo(pedido)){
				pedido.setStatus(StatusPedido.ORCAMENTO);
				pedido.setStatusDesconto(StatusDesconto.EM_APROVACAO);
			}
		    //FIXME: Adicionar Log INFO
			Pedido create = pedidoRepository.save(pedido);
			
			if(pedido.getItens() != null){
				for (Item item  : pedido.getItens()) {
					  item.setPedido(pedido);
					  item.setValorUnitario(item.getProduto().getVlrUnitario());
					  item.setValorTotal(item.calculcarTotais(item));
					  total = total.add(item.calculcarTotais(item));
					  //FIXME: Adicionar Log INFO
					  itemRepository.save(item);
					 				  	
				}
				     totalCalculadoFreteMaisDesconto = pedido.calculaFreteMaisDesconto(pedido, total);
					 if(pedido.isValorMenorQueZero(totalCalculadoFreteMaisDesconto)){
					 throw new ValorTotalMenorQueZero(" a lista de orcamentos deve comter amo menos um item! ");
				}
				 create.setValorTotal(totalCalculadoFreteMaisDesconto);	 
				 //acoes apos gerar pedido salvar e manar email
				 for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
					   acoesAposGerarPedido.executa(create);
				 }
				 			 
			}
		  return create.getId();
		} catch (Exception e) {
		     //FIXME: Adicionar Log ERROR
			 throw new PedidoNotCreateException(" erro ao tentar criar venda! " +e.getMessage());
	   }
	}
		
	@Transactional(rollbackFor = PedidoNotUpdateException.class)
	public void alteraOrcamento(Pedido pedido, Long id) {
	
		try {
			 if(pedido.getStatus().equals(StatusPedido.ORCAMENTO)){
		  		   bf.getBean("orcamento", NivelPedido.class).verificarPedido(pedido, id);			
			 }else if(pedido.getStatus().equals(StatusPedido.EMITIDO)){
				   bf.getBean("emissao", NivelPedido.class).verificarPedido(pedido, id);
			 }else{
				   bf.getBean("cancelamento", NivelPedido.class).verificarPedido(pedido, id);
			 }
	    //FIXME: Adicionar Log ERROR
		} catch (Exception e) {
			throw new PedidoNotUpdateException(" pedido nao pode ser atualizado "  +e.getMessage());
		}
	}
				
}
