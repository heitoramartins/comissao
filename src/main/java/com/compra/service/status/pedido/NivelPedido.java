package com.compra.service.status.pedido;

import com.compra.entity.Pedido;

public interface NivelPedido {
    Pedido verificarPedido (Pedido nivelPedido, Long id);
   	
}
