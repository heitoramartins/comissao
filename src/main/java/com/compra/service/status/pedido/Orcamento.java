package com.compra.service.status.pedido;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.PedidoRepository;

@Component(value="orcamento")
@Configurable
public class Orcamento implements NivelPedido {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;	

	@Override
	public Pedido verificarPedido(Pedido pedido,Long id) {
			BigDecimal total = BigDecimal.ZERO;	
			BigDecimal totalCalculadoFreteMaisDescnto = BigDecimal.ZERO;
			//FIXME: Adicionar Log INFO
			Pedido v = pedidoRepository.findOne(id);	 		
			for (Item item  : pedido.getItens()) {
				item.setPedido(pedido);
				item.setValorUnitario(item.getProduto().getVlrUnitario());
				total = total.add(item.calculcarTotais(item));
				//FIXME: Adicionar Log INFO
				itemRepository.save(item);
			}
			totalCalculadoFreteMaisDescnto = pedido.calculaFreteMaisDesconto(pedido, total);
			if(pedido.isValorMenorQueZero(totalCalculadoFreteMaisDescnto)){
			    throw new ValorTotalMenorQueZero("A lista de orcamentos deve comter amo menos um item");
		    }
		 			
			v.setValorTotal(totalCalculadoFreteMaisDescnto);					
			v.setEnderecoEntrega(pedido.getEnderecoEntrega());					 
			v.setCliente(pedido.getCliente());
			v.setUsuario(pedido.getUsuario());
			v.setValorDesconto(pedido.calculaFreteMaisDesconto(pedido, pedido.getValorTotal()));
			v.setValorFrete(pedido.getValorFrete());
			v.setFormaPagamento(pedido.getFormaPagamento());
			
			//FIXME: Adicionar Log INFO
			return pedidoRepository.save(v);
	
	  }
}