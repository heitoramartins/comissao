package com.compra.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.service.AcoesAposGerarPedido;

@Component
public class PersistePedidoComponent implements AcoesAposGerarPedido{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public void executa(Pedido pedido) {
		 //FIXME: Adicionar Log INFO
		 pedidoRepository.save(pedido);
    }

}
