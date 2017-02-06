package com.compra.status;

import com.compra.entity.Pedido;

public abstract class TemplateGeracaoDePedidos implements RegraPedido{

	@Override
	public Pedido verificarPedido(Pedido pedido, Long id) {
		if(pedido.isOrcamento(pedido)){
			return orcamento(pedido);
		}else if(pedido.isCancelamento(pedido)){
			return cancelamento(pedido);
		}
		return emissao(pedido);
	}
	
	abstract boolean isOrcamento(Pedido pedido);
	abstract boolean isCancelamento(Pedido pedido);
	abstract Pedido orcamento(Pedido pedido);
	abstract Pedido cancelamento(Pedido pedido);
	abstract Pedido emissao(Pedido pedido);
	
	 

}
