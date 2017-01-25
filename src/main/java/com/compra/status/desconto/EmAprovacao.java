package com.compra.status.desconto;

import java.math.BigDecimal;

import com.compra.entity.Pedido;

public class EmAprovacao implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Pedido pedido) {
		return new BigDecimal(0);
	}
	@Override
	public void aprovar(Pedido pedido) {
		pedido.desconto = new Aprovado();
	}
	@Override
	public void reprovar(Pedido pedido) {
		pedido.desconto = new Reprovado();
	}
	@Override
	public void finaliza(Pedido pedido) {
		pedido.desconto = new Finalizado();
		
	}
	
}
