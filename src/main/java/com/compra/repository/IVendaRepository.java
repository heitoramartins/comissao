package com.compra.repository;

import java.util.List;

import com.compra.entity.Venda;

public interface IVendaRepository {
	
	Venda salvar(Venda venda);
	List<Venda> findVendasById(Long id);
	List<Venda> findAll();
	
}
