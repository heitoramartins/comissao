package com.compra.service.emissao;

import com.compra.entity.Venda;

public interface Pedido {

    Venda verificarPedido (Venda venda, Long id);
	
}
