package com.compra.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Venda;
import com.compra.service.VendaService;


@Component
public class VendaBusines {


	@Autowired
	private VendaService vendaService;
	
	public Long salvar(Venda venda){
		return vendaService.salvar(venda);
	}
	
	public List<Venda> findVendasById(Long id){
		List<Venda> vendas = vendaService.findVendasById(id);
		return vendas;
	}
	
	public List<Venda> findAll(){
		List<Venda> vendas = vendaService.findAll();
		return vendas;
	}
	
}
