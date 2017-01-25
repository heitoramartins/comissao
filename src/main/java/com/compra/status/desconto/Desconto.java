package com.compra.status.desconto;

import java.math.BigDecimal;

import com.compra.entity.Pedido;

public interface Desconto {
	 BigDecimal aplicarDescontoExtra(Pedido pedido);
 	 void aprovar(Pedido pedido);
	 void reprovar(Pedido pedido);
	 void finaliza(Pedido pedido);

}
