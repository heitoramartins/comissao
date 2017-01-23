package com.compra.service.status.pedido;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.business.exception.QuantidadeEstoqueExcedenteException;
import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.Venda;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.jdbc.repository.VendaRepository;

@Component(value="emissao")
@Configurable
public class Emissao implements Pedido{

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Venda verificarPedido(Venda venda, Long id) {
		
		    	//FIXME: Adicionar Log INFO
		    	Venda v = vendaRepository.findOne(id);
		       	for (Item item  : v.getItens()) {
		    		//dar baixa no estoque
		    		Integer totalEstoque = 0;
		    		Produto produto = produtoRepository.findOne(item.getProduto().getId());
		    		totalEstoque = subtrairEstoque(item.getQuantidade(), produto);
		    		
		    		produto.setQuantidadeEstoque(totalEstoque);
		    		produtoRepository.save(produto);
		    		
		    		//FIXME: Adicionar Log INFO
		    		itemRepository.save(item);
		    	}
		    	v.setStatus(venda.getStatus());
		    	v.setDataEntrega(LocalDateTime.now());
		    	
		    	//FIXME: Adicionar Log INFO
		    	return vendaRepository.save(v);
	
	}

	private Integer subtrairEstoque(Integer quantidadeEstoque,Produto produto){
		if(quantidadeEstoque > produto.getQuantidadeEstoque()){
			throw new QuantidadeEstoqueExcedenteException(" Quantidade solicitada excede o estoque!");
		}
		Integer total = 0;
		total = produto.getQuantidadeEstoque() - quantidadeEstoque;
		return total;
	}
	
}
