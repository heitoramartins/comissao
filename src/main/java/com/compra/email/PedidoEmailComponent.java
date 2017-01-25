package com.compra.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.service.AcoesAposGerarPedido;
import com.compra.service.PedidoEmailService;

@Component
public class PedidoEmailComponent implements AcoesAposGerarPedido{

	@Autowired
	private PedidoEmailService mailService;
	
	@Override
	public void executa(Pedido pedido) {
		 //FIXME: Adicionar Log INFO
		 mailService.enviar(pedido);
	}
	
}
