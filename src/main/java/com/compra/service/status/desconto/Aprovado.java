package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoException;
import com.compra.entity.Venda;

public class Aprovado implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Venda venda) {
	   return venda.getValorTotal().multiply(new BigDecimal(0.08));		
	}
	@Override
	public void aprovar(Venda venda) {
	  throw new StatusInvalidoException("O Pedido ja foi aprovado!");	
	}
	@Override
	public void reprovar(Venda venda) {
		throw new StatusInvalidoException("Não se pode reprovar um pedido que ja foi aprovado");	
	}
	@Override
	public void finalizar(Venda venda) {
		venda.desconto = new Finalizado();
		
	}

	

}
