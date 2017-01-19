package com.compra.service;

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
import com.compra.repository.IItemRepository;
import com.compra.repository.IVendaRepository;


@Component
public class VendaService {
			
	@Autowired
	private IVendaRepository vendaRepository;
	
	@Autowired
	private IItemRepository itemRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VendaBusines.class);
	
	@Transactional
	public Long salvar(Venda venda){
		try {
			venda.calcularTotais();
			//FIXME: Adicionar Log INFO
			Venda create = vendaRepository.salvar(venda);
			if(venda.getItens() != null){
				for (Item item  : venda.getItens()) {
					 item.setVenda(venda);
					 //FIXME: Adicionar Log INFO
					 itemRepository.salvar(item);
				}
			}
		  return create.getId();
		} catch (Exception e) {
		     //FIXME: Adicionar Log ERROR
			 throw new VendaNotCreateException("erro ao tentar criar venda!");
			
	   }
	}
	
	@Transactional
	public List<Venda> findVendasById(Long id){
		List<Venda> vendas = vendaRepository.findVendasById(id);
		return vendas;
	}
			
	@Transactional
	public List<Venda> findAll(){
		List<Venda> vendas = vendaRepository.findAll();
		return vendas;
	}
	
}
