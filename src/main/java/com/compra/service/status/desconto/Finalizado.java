package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoParaDescontoException;
import com.compra.entity.Pedido;

public class Finalizado implements Desconto{
	

	@Override
	public BigDecimal aplicarDescontoExtra(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta finalizado");
	}
	@Override
	public void aprovar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta finalizado");
	}
	@Override
	public void reprovar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta finalizado");
	}
	@Override
	public void finalizar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Pedido esta finalizado");
	}


}
