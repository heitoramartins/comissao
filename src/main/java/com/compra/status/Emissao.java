package com.compra.status;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.business.exception.QuantidadeEstoqueExcedenteException;
import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.enums.StatusPedido;
import com.compra.entity.Pedido;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.service.AcoesAposGerarPedido;
import com.compra.jdbc.repository.PedidoRepository;

@Component(value="emissao")
@Configurable
public class Emissao implements RegraPedido{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private List<AcoesAposGerarPedido> acoes;
	
	@Override
	public Pedido verificarPedido(Pedido pedido, Long id) {
		
		    	//FIXME: Adicionar Log INFO
		    	Pedido p = pedidoRepository.findOne(id);
		       	for (Item item  : p.getItens()) {
		    		//dar baixa no estoque
		    		Integer totalEstoque = 0;
		    		Produto produto = produtoRepository.findOne(item.getProduto().getId());
		    		totalEstoque = subtrairEstoque(item.getQuantidade(), produto);
		    		
		    		produto.setQuantidadeEstoque(totalEstoque);
		    		produtoRepository.save(produto);
		    		
		    		//FIXME: Adicionar Log INFO
		    		itemRepository.save(item);
		    	}
		    	p.setStatus(StatusPedido.EMITIDO);
		    	p.setDataEntrega(LocalDateTime.now());
		    	
		    	
		    	//acoes apos gerar pedido salvar e manar email
				 for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
					     acoesAposGerarPedido.executa(p);
				}
		return p;		 
	
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
