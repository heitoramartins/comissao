package com.compra.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "nome")
	@JsonView(Views.Public.class)
	private String nome;
	
	@Column(name = "vlr_unitario")
	@JsonView(Views.Public.class)
	private BigDecimal vlrUnitario;
	
	@Column(name = "qtde_estoque")
	@JsonView(Views.Public.class)
	private Integer quantidadeEstoque;
	
	@ManyToOne
	@JoinColumn(name = "fk_categoria")
	@JsonView(Views.Public.class)
	private Categoria categoria;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getVlrUnitario() {
		return vlrUnitario;
	}
	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
		
	}
