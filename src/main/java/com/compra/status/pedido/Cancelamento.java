package com.compra.status.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.entity.Pedido;
import com.compra.entity.Produto;
import com.compra.entity.enums.StatusPedido;
import com.compra.jdbc.repository.PedidoRepository;

@Component(value="cancelamento")
@Configurable
public class Cancelamento implements NivelPedido{

	@Autowired
	private PedidoRepository pedidoRepository;
		
	/*@Autowired
	private ProdutoRepository produtoRepository;*/

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
			v.setStatus(StatusPedido.CANCELADO);
			return pedidoRepository.save(v);
		
	  
	}
	
	private Integer estornoEstoque(Integer quantidadeEstoque, Produto produto){
		Integer total = 0;
		total = quantidadeEstoque + produto.getQuantidadeEstoque();
		return total;
	}

		
	
}
