package com.compra.jdbc.dao;

import java.util.List;

import com.compra.entity.Venda;

public interface VendaDAO {
	
	Venda salvar(Venda venda);
	List<Venda> findVendasById(Long id);
	List<Venda> findAll();
    Venda findById(Long id);
		
	
	
}
