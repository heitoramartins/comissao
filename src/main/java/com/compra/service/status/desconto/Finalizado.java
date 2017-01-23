package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoException;
import com.compra.entity.Venda;

public class Finalizado implements Desconto{
	

	@Override
	public BigDecimal aplicarDescontoExtra(Venda venda) {
		throw new StatusInvalidoException("Pedido esta finalizado");
	}
	@Override
	public void aprovar(Venda venda) {
		throw new StatusInvalidoException("Pedido esta finalizado");
	}
	@Override
	public void reprovar(Venda venda) {
		throw new StatusInvalidoException("Pedido esta finalizado");
	}
	@Override
	public void finalizar(Venda venda) {
		throw new StatusInvalidoException("Pedido esta finalizado");
	}


}
