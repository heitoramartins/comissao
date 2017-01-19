package com.compra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private double vlrUnitario;
		
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
	public double getVlrUnitario() {
		return vlrUnitario;
	}
	public void setVlrUnitario(double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}
	
	
	
	}
