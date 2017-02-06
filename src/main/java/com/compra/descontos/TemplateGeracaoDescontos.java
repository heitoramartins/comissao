package com.compra.descontos;

import java.math.BigDecimal;

import com.compra.entity.Pedido;

public abstract class TemplateGeracaoDescontos implements IDescontos{
	
	@Override
	public BigDecimal calculaFreteMaisDesconto(Pedido pedido, BigDecimal total) {
		    return primeiraCompraDesconto(pedido, total);
	}
	
     abstract BigDecimal primeiraCompraDesconto(Pedido pedido, BigDecimal total);
	
	
}
