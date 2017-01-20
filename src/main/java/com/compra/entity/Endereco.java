package com.compra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "endereco")
public class Endereco{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="logradouro")
	@JsonView(Views.Public.class)
	private String logradouro;
	
	@Column(name="numero")
	@JsonView(Views.Public.class)
	private String numero;
	
	@Column(name="complemento")
	@JsonView(Views.Public.class)
	private String complemento;
	
	@Column(name="cidade")
	@JsonView(Views.Public.class)
	private String cidade;
	
	@Column(name="uf")
	@JsonView(Views.Public.class)
	private String uf;
	
	@Column(name="cep")
	@JsonView(Views.Public.class)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}