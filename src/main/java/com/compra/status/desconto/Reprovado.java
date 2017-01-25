package com.compra.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoParaDescontoException;
import com.compra.entity.Pedido;

public class Reprovado implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta reprovado");
	}
	@Override
	public void aprovar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta reprovado");
	}
	@Override
	public void reprovar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta reprovado");
    }
	@Override
	public void finaliza(Pedido pedido) {
		pedido.desconto = new Finalizado();
		
	}
}
