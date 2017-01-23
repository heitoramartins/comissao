package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoParaDescontoException;
import com.compra.entity.Pedido;

public class Aprovado implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Pedido pedido) {
	   return pedido.getValorTotal().multiply(new BigDecimal(0.08));		
	}
	@Override
	public void aprovar(Pedido pedido) {
	  throw new StatusInvalidoParaDescontoException("O Pedido ja foi aprovado!");	
	}
	@Override
	public void reprovar(Pedido pedido) {
		throw new StatusInvalidoParaDescontoException("Não se pode reprovar um pedido que ja foi aprovado");	
	}
	@Override
	public void finalizar(Pedido pedido) {
		pedido.desconto = new Finalizado();
		
	}

	

}
