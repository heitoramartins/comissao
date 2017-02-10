package com.compra.jdbc.dao;

import com.compra.entity.Pedido;

public interface PedidoDAO {
	
	Pedido salvar(Pedido pedido);
    Pedido findById(Long id);
		
	
	
}
