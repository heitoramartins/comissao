package com.compra.service.status.pedido;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.entity.Item;
import com.compra.entity.Venda;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.VendaRepository;

@Component(value="orcamento")
@Configurable
public class Orcamento implements Pedido {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemRepository itemRepository;	

	@Override
	public Venda verificarPedido(Venda venda,Long id) {
			BigDecimal total = BigDecimal.ZERO;	
			BigDecimal subtotal = BigDecimal.ZERO;
			//FIXME: Adicionar Log INFO
			Venda v = vendaRepository.findOne(id);	 		
			for (Item item  : venda.getItens()) {
				item.setVenda(venda);
				item.setValorUnitario(item.getProduto().getVlrUnitario());
				total = total.add(item.calculcarTotais(item));
				//FIXME: Adicionar Log INFO
				itemRepository.save(item);
			}
			/*subtotal = subtotal.add(venda.calculaFreteMaisDesconto(venda, total));
			if(venda.isValorMenorQueZero(subtotal)){
				throw new ValorTotalMenorQueZero("A lista de orcamentos deve comter amo menos um item");
			}*/
			
			v.setValorTotal(subtotal);					
			v.setEnderecoEntrega(venda.getEnderecoEntrega());					 
			v.setCliente(venda.getCliente());
			v.setUsuario(venda.getUsuario());
			v.setValorDesconto(venda.getValorDesconto());
			v.setValorFrete(venda.getValorFrete());
			v.setFormaPagamento(venda.getFormaPagamento());
			
			//FIXME: Adicionar Log INFO
			return vendaRepository.save(v);
	
	  }
}