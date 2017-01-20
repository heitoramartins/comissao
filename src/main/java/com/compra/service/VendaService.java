package com.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.compra.business.VendaBusines;
import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.business.exception.VendaNotCreateException;
import com.compra.business.exception.VendaNotUpdateException;
import com.compra.entity.Item;
import com.compra.entity.Venda;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.dao.VendaDAO;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.VendaRepository;
import com.compra.service.emissao.Pedido;


@Component
public class VendaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendaBusines.class);
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
		
	@Autowired
	private BeanFactory bf;
	
	@Transactional
	public List<Venda> findVendasById(Long id){
		List<Venda> vendas = vendaDAO.findVendasById(id);
		return vendas;
	}
			
	@Transactional
	public List<Venda> findAll(){
		List<Venda> vendas = vendaDAO.findAll();
		return vendas;
	}
	
	@Transactional
	public Long salvarOrcamento(Venda venda){
		BigDecimal total = BigDecimal.ZERO;	
		BigDecimal subtotal = BigDecimal.ZERO;	
		try {
			if(venda.isNovo(venda)){
				venda.setStatus(StatusPedido.ORCAMENTO);
			}
		    //FIXME: Adicionar Log INFO
			Venda create = vendaRepository.save(venda);
			
			if(venda.getItens() != null){
				for (Item item  : venda.getItens()) {
					  item.setVenda(venda);
					  item.setValorUnitario(item.getProduto().getVlrUnitario());
					  total = total.add(item.calculcarTotais(item));
					  //FIXME: Adicionar Log INFO
					  itemRepository.save(item);
					 				  	
				}
				 subtotal = subtotal.add(venda.calculaFreteMaisDesconto(venda, total));
					 if(venda.isValorMenorQueZero(subtotal)){
					 throw new ValorTotalMenorQueZero("A lista de orcamentos deve comter amo menos um item");
				 }
				 
				 //FIXME: Adicionar Log INFO
				 vendaRepository.updateTotais(subtotal, venda.getId());
			}
		  return create.getId();
		} catch (Exception e) {
		     //FIXME: Adicionar Log ERROR
			 throw new VendaNotCreateException("erro ao tentar criar venda!");
	   }
	}
	
	
	@Transactional(rollbackFor = VendaNotUpdateException.class)
	public void alteraOrcamento(Venda venda, Long id) {
	
		try {
		  	   if(venda.getStatus().equals(StatusPedido.ORCAMENTO)){
		  		   bf.getBean("orcamento", Pedido.class).verificarPedido(venda, id);			
			   }else if(venda.getStatus().equals(StatusPedido.EMITIDO)){
				   bf.getBean("emissao", Pedido.class).verificarPedido(venda, id);
			   }else 
				   bf.getBean("cancelamento", Pedido.class).verificarPedido(venda, id);
			  					
		//FIXME: Adicionar Log ERROR
		} catch (Exception e) {
			throw new VendaNotUpdateException("pedido nao pode ser atualizado");
		}
		
	}
	
}
