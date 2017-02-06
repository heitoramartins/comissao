package com.compra.descontos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;

@Component
public class Descontos implements IDescontos{

	@Override //desconto de 5% na primeira compra
	public BigDecimal calculaFreteMaisDesconto(Pedido pedido, BigDecimal total) {
	           BigDecimal desconto = BigDecimal.ZERO;
			   desconto = total.multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.UP);
			   pedido.setValorDesconto(desconto);
			   total = total.subtract(desconto).add(pedido.getValorFrete());
			   pedido.setValorTotal(total);
			   return total.setScale(2, RoundingMode.UP);//arredondas casas decimais
	}

		
}
