package com.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.business.exception.EmailPedidoNotCreateException;
import com.compra.entity.Pedido;
import com.compra.entity.enums.StatusDesconto;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.PedidoRepository;

@Component
public class DescontoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private List<AcoesAposGerarPedido> acoes;
	
	public void aplicarDescontoExtra(Long id) {
		  BigDecimal totalMaisDescontoExtra = BigDecimal.ZERO;
		  Pedido pedido = pedidoRepository.findOne(id);
		  try {
			if(pedido.getStatus().equals(StatusPedido.ORCAMENTO) &&
					  pedido.getStatusDesconto().equals(StatusDesconto.EM_APROVACAO)){
				      totalMaisDescontoExtra = pedido.calculaFreteMaisDescontoExtraAprovado(pedido, pedido.getValorTotal());
				      pedido.setValorTotal(totalMaisDescontoExtra);
			          pedido.setStatusDesconto(StatusDesconto.APROVADO);		
			    	  pedido.finaliza();
			          //salva e manda email
			          for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
						   acoesAposGerarPedido.executa(pedido);
					}
			  }
		} catch (Exception e) {
			throw new EmailPedidoNotCreateException(" erro não foi possivel atualizar o status nem validar o desconto extra "+e.getMessage());
		}
	}
    
	public void reprovarDescontoExtra(Long id) {
		  Pedido pedido = pedidoRepository.findOne(id);
		  
		  try {
			if(pedido.getStatus().equals(StatusPedido.ORCAMENTO) 
					&& pedido.getStatusDesconto().equals(StatusDesconto.EM_APROVACAO)){
				    pedido.setStatusDesconto(StatusDesconto.REPROVADO);
				    pedido.finaliza();
				    //salva e manda email
				    for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
						acoesAposGerarPedido.executa(pedido);
					}

			  }
		 } catch (Exception e) {
			 throw new EmailPedidoNotCreateException(" erro não foi possivel atualizar o status  "+e.getMessage());
		}
	}

}
