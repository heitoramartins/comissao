package com.compra.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.service.DescontoService;
import com.compra.service.PedidoService;


@Component
public class PedidoBusines {


	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DescontoService descontoService;
	
	public Long salvar(Pedido pedido){
		return pedidoService.salvarOrcamento(pedido);
	}
	
	public List<Pedido> findVendasById(Long id){
		List<Pedido> pedidos = pedidoService.findVendasById(id);
		return pedidos;
	}
	
	public List<Pedido> findAll(){
		List<Pedido> pedidos = pedidoService.findAll();
		return pedidos;
	}

	public void alteraOrcamento(Pedido pedido, Long id) {
		pedidoService.alteraOrcamento(pedido, id);
    }

	public void emissaoPedido(Long id) {
		descontoService.aplicarDescontoExtra(id);
		
	}

	public void cancelamentoPedido(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void finalizadoPedido(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
