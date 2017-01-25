package com.compra.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_produto")
	@JsonView(Views.Public.class)
	private Produto produto;
	
	@ManyToOne(targetEntity = Pedido.class)
	@JoinColumn(name = "fk_pedido")
	private Pedido pedido;
	
	@Column(name = "quantidade")
	@JsonView(Views.Public.class)
	private Integer quantidade;
	
	@Column(name = "vlr_unitario")
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	
	@Column(name = "vlr_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
			
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
		
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
		
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Transient
	public BigDecimal calculcarTotais(Item item){
		BigDecimal total = BigDecimal.ZERO;
		total = total.add(item.getProduto().getVlrUnitario().multiply(new BigDecimal(item.getQuantidade())));
		return total;
	}
		
	
}
