package com.compra.entity.enums;

import com.compra.service.emissao.Cancelamento;
import com.compra.service.emissao.Emissao;
import com.compra.service.emissao.Orcamento;
import com.compra.service.emissao.Pedido;

public enum StatusPedido {

	ORCAMENTO("Orçamento",new Orcamento()), 
	EMITIDO("Emitido", new Emissao()), 
	CANCELADO("Cancelado", new Cancelamento());
	
	private String descricao;
	private Pedido pedido;
	
	StatusPedido(String descricao,Pedido pedido) {
		this.descricao = descricao;
		this.pedido = pedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	
	
}
