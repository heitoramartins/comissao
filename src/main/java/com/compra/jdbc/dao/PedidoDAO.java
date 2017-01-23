package com.compra.jdbc.dao;

import java.util.List;

import com.compra.entity.Pedido;

public interface PedidoDAO {
	
	Pedido salvar(Pedido pedido);
	List<Pedido> findVendasById(Long id);
	List<Pedido> findAll();
    Pedido findById(Long id);
		
	
	
}
