package com.compra.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class EnderecoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "entrega_logradouro",length = 150)
	@JsonView(Views.Public.class)
	private String logradouro;
	
	@Column(name = "entrega_numero", nullable = false, length = 20)
	@JsonView(Views.Public.class)
	private String numero;
	
	@Column(name = "entrega_complemento", length = 150)
	@JsonView(Views.Public.class)
	private String complemento;
	
	@Column(name = "entrega_cidade", nullable = false, length = 60)
	@JsonView(Views.Public.class)
	private String cidade;
	
	@Column(name = "entrega_uf", nullable = false, length = 60)
	@JsonView(Views.Public.class)
	private String uf;
	
	@Column(name = "entrega_cep", nullable = false, length = 9)
	@JsonView(Views.Public.class)
	private String cep;

	
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

}
