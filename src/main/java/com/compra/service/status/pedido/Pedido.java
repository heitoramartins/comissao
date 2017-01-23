package com.compra.service.status.pedido;

import com.compra.entity.Venda;

public interface Pedido {
    Venda verificarPedido (Venda venda, Long id);
   	
}
