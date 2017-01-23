package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.business.exception.StatusInvalidoException;
import com.compra.entity.Venda;

public class Reprovado implements Desconto{
	
	@Override
	public BigDecimal aplicarDescontoExtra(Venda venda) {
		throw new StatusInvalidoException("Pedido esta reprovado");
	}
	@Override
	public void aprovar(Venda venda) {
		throw new StatusInvalidoException("Pedido esta reprovado");
	}
	@Override
	public void reprovar(Venda venda) {
		throw new StatusInvalidoException("Pedido esta reprovado");
    }
	@Override
	public void finalizar(Venda venda) {
		venda.desconto = new Finalizado();
		
	}

	
}
