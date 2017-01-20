package com.compra.service.emissao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.Venda;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.jdbc.repository.VendaRepository;

@Component(value="emissao")
public class Emissao implements Pedido{

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Venda verificarPedido(Venda venda, Long id) {
		 BigDecimal total = BigDecimal.ZERO;	
		 BigDecimal subtotal = BigDecimal.ZERO;
		 Integer totalEstoque = 0;
		 
		 //FIXME: Adicionar Log INFO
		 Venda v = vendaRepository.findOne(id);
		 
		   for (Item item  : venda.getItens()) {
					  item.setVenda(venda);
					  item.setValorUnitario(item.getProduto().getVlrUnitario());
					  total = total.add(item.calculcarTotais(item));
					  
					  //dar baixa no estoque
					  totalEstoque += item.getProduto().subtrairEstoque(item.getQuantidade());
					  Produto produto = produtoRepository.findOne(item.getProduto().getId());
					  produto.setQuantidadeEstoque(totalEstoque);
					  produtoRepository.save(produto);
					  
					  //FIXME: Adicionar Log INFO
					  itemRepository.save(item);
				}
				 subtotal = subtotal.add(venda.calculaFreteMaisDesconto(venda, total));
					 if(venda.isValorMenorQueZero(subtotal)){
					 throw new ValorTotalMenorQueZero("A lista de orcamentos deve comter amo menos um item");
				 }
				 v.setStatus(StatusPedido.EMITIDO);
				 v.setDataEntrega(LocalDateTime.now());
												 				 
				 //FIXME: Adicionar Log INFO
			  return vendaRepository.save(v);
	}

}
