package com.compra.service.emissao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.Venda;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.jdbc.repository.VendaRepository;

@Component(value="cancelamento")
@Configurable
public class Cancelamento implements Pedido{

	@Autowired
	private VendaRepository vendaRepository;
		
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Venda verificarPedido(Venda venda, Long id) {
		
		//FIXME: Adicionar Log INFO
		Venda v = vendaRepository.findOne(id);
		if(!venda.getItens().isEmpty()){
				 for (Item item : venda.getItens()) {
				  // estorno no estoque
					  Integer total = 0;
					  Produto produto = produtoRepository.findOne(item.getProduto().getId());
					  total = estornoEstoque(item.getQuantidade(), produto);
					  produto.setQuantidadeEstoque(total);
					//FIXME: Adicionar Log INFO
					  produtoRepository.save(produto);
				}
			}
			 v.setStatus(StatusPedido.CANCELADO);
			 v.setDataVenda(venda.getDataVenda());
			 v.setDataEntrega(venda.getDataEntrega());
			 v.setValorTotal(venda.getValorTotal());
		   return vendaRepository.save(venda);
	  
	}
	
	private Integer estornoEstoque(Integer quantidadeEstoque, Produto produto){
		Integer total = 0;
		total = quantidadeEstoque + produto.getQuantidadeEstoque();
		return total;
	}	
	
	
}
