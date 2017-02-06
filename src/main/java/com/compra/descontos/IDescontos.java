package com.compra.descontos;

import java.math.BigDecimal;

import com.compra.entity.Pedido;

public interface IDescontos {
	BigDecimal calculaFreteMaisDesconto(Pedido pedido, BigDecimal total);
	
}
