package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoParaDescontoException;
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
	public void finalizar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("O peido precisa ser primeiro aprovado para depois ser finalizado!");
		
	}


}
