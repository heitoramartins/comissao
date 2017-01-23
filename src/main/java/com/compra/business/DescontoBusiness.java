package com.compra.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.service.DescontoService;

@Component
public class DescontoBusiness {
	
	@Autowired
	private DescontoService descontoService;
	
	public void alteraOrcamento(Pedido pedido, Long id) {
		descontoService.aplicarDescontoExtra(pedido, id);
		
	}

}
