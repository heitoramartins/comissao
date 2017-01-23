package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoException;
import com.compra.entity.Venda;

public class EmAprovacao implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Venda venda) {
		return venda.getValorTotal().multiply(new BigDecimal(0.05));
	}
	@Override
	public void aprovar(Venda venda) {
		venda.desconto = new Aprovado();
	}
	@Override
	public void reprovar(Venda venda) {
		venda.desconto = new Reprovado();
	}
	@Override
	public void finalizar(Venda venda) {
		throw new StatusInvalidoException("O peido precisa ser primeiro aprovado para depois ser finalizado!");
		
	}


}
