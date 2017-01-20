package com.compra.entity;

import java.beans.Transient;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_produto")
	@JsonView(Views.Public.class)
	private Produto produto;
	
	@ManyToOne(targetEntity = Venda.class)
	@JoinColumn(name = "fk_venda")
	private Venda venda;
	
	@Column(name = "quantidade")
	@JsonView(Views.Public.class)
	private Integer quantidade;
	
	@Column(name = "vlr_unitario")
	private BigDecimal valorUnitario = BigDecimal.ZERO;
			
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
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	@Transient
	public BigDecimal calculcarTotais(Item item){
		BigDecimal total = BigDecimal.ZERO;
		total = total.add(item.getProduto().getVlrUnitario().multiply(new BigDecimal(item.getQuantidade())));
		return total;
	}
	
	
		
	
}
