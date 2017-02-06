package com.compra.status;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;

@Component
public class GerarPedido extends TemplateGeracaoDePedidos{
	
	@Autowired
	private BeanFactory bf;
	
	@Override
	boolean isOrcamento(Pedido pedido) {
		return pedido.isOrcamento(pedido);
	}

	@Override
	boolean isCancelamento(Pedido pedido) {
		return pedido.isCancelamento(pedido);
	}

	@Override
	Pedido orcamento(Pedido pedido) {
	  return  bf.getBean("orcamento", RegraPedido.class)
			  .verificarPedido(pedido, pedido.getId());	
	}

	@Override
	Pedido cancelamento(Pedido pedido) {
		return  bf.getBean("cancelamento", RegraPedido.class)
				.verificarPedido(pedido, pedido.getId());
	}

	@Override
	Pedido emissao(Pedido pedido) {
		return  bf.getBean("emissao", RegraPedido.class)
				.verificarPedido(pedido, pedido.getId());
	}


}
