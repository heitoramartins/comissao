package com.compra.service.emissao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.Venda;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.jdbc.repository.VendaRepository;

@Component(value="cancelamento")
public class Cancelamento implements Pedido{

	@Autowired
	private VendaRepository vendaRepository;
		
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Venda verificarPedido(Venda venda, Long id) {
		Integer total = 0;
		//FIXME: Adicionar Log INFO
		Venda v = vendaRepository.findOne(id);
		if(!venda.getItens().isEmpty()){
				 for (Item item : venda.getItens()) {
					 
					  // estorno no estoque
					  total +=  item.getProduto().estornoEstoque(item.getQuantidade());
					  Produto produto = produtoRepository.findOne(item.getProduto().getId());
					  produto.setQuantidadeEstoque(total);
					//FIXME: Adicionar Log INFO
					  produtoRepository.save(produto);
				}
			}
			 v.setStatus(StatusPedido.CANCELADO);
		   return vendaRepository.save(venda);
	  
	}
	
	
	
	
}
