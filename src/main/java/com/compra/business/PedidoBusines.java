package com.compra.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.service.PedidoService;


@Component
public class PedidoBusines {


	@Autowired
	private PedidoService pedidoService;
			
	public Long salvar(Pedido pedido){
		return pedidoService.salvarOrcamento(pedido);
	}
	
		
	public Pedido findById(Long id){
		Pedido pedido = pedidoService.findById(id);
		return pedido;
	}
		
	public void alteraOrcamento(Pedido pedido, Long id) {
		pedidoService.alteraOrcamento(pedido, id);
    }

	
	
}
