package com.compra.builder;

import java.math.BigDecimal;

import com.compra.entity.Pedido;
import com.compra.entity.Produto;

public class ItemBuilder {
	
	
	private Long id;
	private Produto produto;
	private Pedido pedido;
	private Integer quantidade;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public ItemBuilder idItem(Long id) {
		this.id = id;
		return this;
	}

	public ItemBuilder itemProduto(Produto produto) {
		this.produto = produto;
		return this;
	}
	
	public ItemBuilder itemPedido(Pedido pedido) {
		this.pedido = pedido;
		return this;
	}
	
	public ItemBuilder itemQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	public ItemBuilder itemValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
		return this;
	}
	
	public ItemBuilder itemValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}
		
	public BigDecimal cauculaTotais(){
		return produto.getVlrUnitario().multiply(new BigDecimal(quantidade));
	}
	
	
}
