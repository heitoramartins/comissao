package com.compra.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.entity.enums.StatusDesconto;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.PedidoRepository;

@Component
public class DescontoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void aplicarDescontoExtra(Long id) {
		  BigDecimal totalMaisDescontoExtra = BigDecimal.ZERO;
		  Pedido pedido = pedidoRepository.findOne(id);
		  if(pedido.getStatus().equals(StatusPedido.ORCAMENTO) &&
				  pedido.getStatusDesconto().equals(StatusDesconto.EM_APROVACAO)){
			      totalMaisDescontoExtra = pedido.calculaFreteMaisDescontoExtraAprovado(pedido, pedido.getValorTotal());
			      pedido.setValorTotal(totalMaisDescontoExtra);
                  pedido.setStatusDesconto(StatusDesconto.APROVADO);		
		    	  pedidoRepository.save(pedido);
		  }
		  
		
	}

	
	
}
