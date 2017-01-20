package com.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.compra.business.VendaBusines;
import com.compra.business.exception.VendaNotCreateException;
import com.compra.entity.Item;
import com.compra.entity.Venda;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.dao.VendaDAO;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.VendaRepository;


@Component
public class VendaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendaBusines.class);
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
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
				 //FIXME: Adicionar Log INFO
				 vendaRepository.updateTotais(subtotal, venda.getId());
			}
		  return create.getId();
		} catch (Exception e) {
		     //FIXME: Adicionar Log ERROR
			 throw new VendaNotCreateException("erro ao tentar criar venda!");
	   }
	}
		
		
	
}
