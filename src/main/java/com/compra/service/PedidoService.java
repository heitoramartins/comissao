package com.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.compra.business.exception.PedidoNotCreateException;
import com.compra.business.exception.PedidoNotUpdateException;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.entity.Produto;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.dao.PedidoDAO;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.status.GerarPedido;


@Component
public class PedidoService {
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private GerarPedido gerarPedido;
	
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
		    //FIXME: Adicionar Log INFO
			Pedido create = pedidoRepository.save(pedido);
			if(pedido.getItens() != null){
				for (Item item  : pedido.getItens()) {
					  Produto produto = produtoRepository.findOne(item.getProduto().getId());
					 
					  item.setPedido(pedido);
					  item.setValorUnitario(produto.getVlrUnitario());
					  item.setValorTotal(item.calculcarTotais(item, produto));
					  total = total.add(item.calculcarTotais(item, produto));
					 				  
					  //FIXME: Adicionar Log INFO
					  itemRepository.save(item);
				}
				
				 Pedido p = pedidoRepository.findOne(pedido.getId());
				 totalCalculadoFreteMaisDesconto = pedido.calculaFreteMaisDesconto(p, total);
				 p.setValorTotal(totalCalculadoFreteMaisDesconto);	 
				 p.setStatus(StatusPedido.ORCAMENTO);
											
				 //acoes apos gerar pedido salvar e manar email
				 for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
					     acoesAposGerarPedido.executa(p);
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
			
	    //FIXME: Adicionar Log ERROR
		} catch (Exception e) {
			throw new PedidoNotUpdateException(" pedido nao pode ser atualizado "  +e.getMessage());
		}
	}
	
	
	
				
}
