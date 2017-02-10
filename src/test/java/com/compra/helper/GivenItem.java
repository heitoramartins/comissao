package com.compra.helper;

import java.util.Arrays;
import java.util.List;

import com.compra.entity.Item;
import com.compra.entity.Pedido;

public class GivenItem {
	
	 public static Item xbox() {
	        Item item = new Item();
	       	item.setId(1L);
			item.setProduto(GivenProduto.xbox());
			item.setQuantidade(1);
			item.setValorUnitario(GivenProduto.xbox().getVlrUnitario());
			return item;
	   }
	 
	 public static Item geladeira() {
	        Item item = new Item();
	       	item.setId(2L);
			item.setProduto(GivenProduto.xbox());
			item.setQuantidade(1);
			item.setValorUnitario(GivenProduto.xbox().getVlrUnitario());
			return item;
	   }
	 
	 public Pedido criarListaComUmItem(){
		 Pedido pedido = new Pedido();
		 List<Item> itens = Arrays.asList(geladeira());
		 for (Item item : itens) {
			 pedido.getItens().add(item);
		}
		 return pedido;
	 }

}
