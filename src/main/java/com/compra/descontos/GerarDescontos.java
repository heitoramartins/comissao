package com.compra.descontos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;

@Component
public class GerarDescontos extends TemplateGeracaoDescontos {
	
	@Autowired
	private Descontos descontos;
	
	@Override
	BigDecimal primeiraCompraDesconto(Pedido pedido, BigDecimal total) {
		return descontos.calculaFreteMaisDesconto(pedido, total);
	}


}
