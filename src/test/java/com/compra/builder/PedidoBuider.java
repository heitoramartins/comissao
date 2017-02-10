package com.compra.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.compra.entity.Cliente;
import com.compra.entity.EnderecoEntrega;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.entity.Produto;
import com.compra.entity.Usuario;
import com.compra.entity.enums.FormaPagamento;
import com.compra.helper.GivenItem;
import com.compra.helper.GivenProduto;

public class PedidoBuider {
   
	private Long idPedido;
	private List<Item> itens;
	private LocalDateTime dataPedido;
	private Usuario usuario;
	private Cliente cliente;
	private BigDecimal valorFrete = BigDecimal.ZERO;
	private FormaPagamento formaPagamento;
	private EnderecoEntrega enderecoEntrega;
	private Produto produto;
    private Long idItem;
	
	public PedidoBuider() {
		this.dataPedido = LocalDateTime.now();
		itens = new ArrayList<>();
	}
	
	public PedidoBuider idPedido(Long idPedido){
		this.idPedido = idPedido;
		return this;		
	}
	
	public PedidoBuider idItem(Long idItem){
		this.idItem = idItem;
		return this;		
	}
	
	public PedidoBuider paraUsuario(Usuario usuario){
		this.usuario = usuario;
		return this;		
	}
	
	public PedidoBuider paraCliente(Cliente cliente){
		this.cliente = cliente;
		return this;		
	}
	
	public PedidoBuider comProduto(Produto produto){
		this.produto = produto;
		return this;		
	}
		
	public PedidoBuider dataPedido(LocalDateTime dataPedido){
		this.dataPedido = dataPedido;
		return this;		
	}
	
	public PedidoBuider enderecoEntrega(EnderecoEntrega enderecoEntrega){
		this.enderecoEntrega = enderecoEntrega;
		return this;		
	}
	
	public PedidoBuider formaDePagamento(FormaPagamento formaPagamento){
		this.formaPagamento = formaPagamento;
		return this;		
	}
	
	public PedidoBuider valorDoFrete(BigDecimal valorFrete ){
		this.valorFrete = valorFrete;
		return this;		
	}
			
	public PedidoBuider adicionarItem(Item item){
		itens.add(item);
		return this;
	}
			
	public Pedido buscaPedido() {
		Pedido pedido = findPedido();
		for (Item item : itens) {
			 item.setId(idItem);
			 item.setPedido(pedido);
			 item.setProduto(produto);
			 pedido.getItens().add(item);
	 	}
		return pedido;
	}

	private Pedido findPedido() {
		Pedido pedido = new Pedido();
		pedido.setId(idPedido);
		pedido.setDataVenda(dataPedido);
		pedido.setCliente(cliente);
		pedido.setUsuario(usuario);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setValorFrete(valorFrete);
		pedido.setEnderecoEntrega(enderecoEntrega);
		return pedido;
	}
	
	public Pedido buscaPedidoValidaLista() {
		BigDecimal total = BigDecimal.ZERO;	
	   	Pedido pedido = findPedido();
		for (Item item : itens) {
			 item.setId(idItem);
			 item.setPedido(pedido);
			 for (Item i : itens) {
				 item.setProduto(i.getProduto());
			 }
			 item.setValorUnitario(item.getProduto().getVlrUnitario());
			 item.setValorTotal(item.calculcarTotais(item, item.getProduto()));
			 total = total.add(item.calculcarTotais(item, item.getProduto()));
			 pedido.getItens().add(item);
	 	}
		return pedido;
	}
	
	
		
	

	
	
	
}
