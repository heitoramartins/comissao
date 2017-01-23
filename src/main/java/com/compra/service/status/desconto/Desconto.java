package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.entity.Venda;

public interface Desconto {
	 BigDecimal aplicarDescontoExtra(Venda venda);
 	 void aprovar(Venda venda);
	 void reprovar(Venda venda);
	 void finalizar(Venda venda);
}
