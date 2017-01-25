package com.compra.status.pedido;

import com.compra.entity.Pedido;

public interface NivelPedido {
    Pedido verificarPedido (Pedido nivelPedido, Long id);
   	
}
