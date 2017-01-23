package com.compra.service.status.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.entity.Item;
import com.compra.entity.Produto;
import com.compra.entity.Pedido;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.jdbc.repository.PedidoRepository;

@Component(value="cancelamento")
@Configurable
public class Cancelamento implements NivelPedido{

	@Autowired
	private PedidoRepository pedidoRepository;
		
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Pedido verificarPedido(Pedido pedido, Long id) {
					
			Pedido v = pedidoRepository.findOne(id);
			//fazer logica para estornar o estoque
			/*for (Item item : pedido.getItens()) {
					// estorno no estoque
					Integer total = 0;
					Produto produto = produtoRepository.findOne(item.getProduto().getId());
					total = estornoEstoque(item.getQuantidade(), produto);
					produto.setQuantidadeEstoque(total);
					//FIXME: Adicionar Log INFO
					produtoRepository.save(produto);
			}*/
			
			v.setStatus(pedido.getStatus());
			v.setDataVenda(v.getDataVenda());
			return pedidoRepository.save(pedido);
		
	  
	}
	
	private Integer estornoEstoque(Integer quantidadeEstoque, Produto produto){
		Integer total = 0;
		total = quantidadeEstoque + produto.getQuantidadeEstoque();
		return total;
	}

		
	
}
