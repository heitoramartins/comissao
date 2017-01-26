package com.compra.status.pedido;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.compra.business.exception.ValorTotalMenorQueZero;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.entity.Produto;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.service.AcoesAposGerarPedido;

@Component(value="orcamento")
@Configurable
public class Orcamento implements NivelPedido {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private List<AcoesAposGerarPedido> acoes;

	@Override
	public Pedido verificarPedido(Pedido pedido,Long id) {
			BigDecimal total = BigDecimal.ZERO;	
			BigDecimal totalCalculadoFreteMaisDescnto = BigDecimal.ZERO;
			//FIXME: Adicionar Log INFO
			Pedido p = pedidoRepository.findOne(id);	
				
			Item i = null;
			for (Item item  : pedido.getItens()) {
				 i = new Item();
				 i.setId(item.getId());
				 i.setPedido(pedido);
				 Produto produto = produtoRepository.findOne(item.getProduto().getId());
				 i.setProduto(produto);
				 i.setQuantidade(item.getQuantidade());
				 i.setValorUnitario(produto.getVlrUnitario());
				 i.setValorTotal(i.calculcarTotais(item, produto));
				 total = total.add(i.calculcarTotais(item, produto));
				 //FIXME: Adicionar Log INFO
				 itemRepository.save(i);
			}
			
			totalCalculadoFreteMaisDescnto = pedido.calculaFreteMaisDesconto(p, total);
			if(pedido.isValorMenorQueZero(totalCalculadoFreteMaisDescnto)){
			    throw new ValorTotalMenorQueZero("A lista de orcamentos deve comter amo menos um item");
		    }
		 			
			p.setValorTotal(totalCalculadoFreteMaisDescnto);					
			p.setEnderecoEntrega(pedido.getEnderecoEntrega());					 
			p.setCliente(pedido.getCliente());
			p.setUsuario(pedido.getUsuario());
			p.setValorDesconto(pedido.calculaFreteMaisDesconto(pedido, pedido.getValorTotal()));
			p.setValorFrete(pedido.getValorFrete());
			p.setFormaPagamento(pedido.getFormaPagamento());
			
		
			 //acoes apos gerar pedido salvar e manar email
			 for (AcoesAposGerarPedido acoesAposGerarPedido : acoes) {
				     acoesAposGerarPedido.executa(p);
			}
			
			return p;
	
	  }
		
}