package com.compra.service.status.desconto;

import java.math.BigDecimal;

import com.compra.entity.Pedido;

public interface Desconto {
	 BigDecimal aplicarDescontoExtra(Pedido pedido);
 	 void aprovar(Pedido pedido);
	 void reprovar(Pedido pedido);
	 void finalizar(Pedido pedido);
}
