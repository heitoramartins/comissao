package com.compra.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.compra.entity.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "nome")
	@JsonView(Views.Public.class)
	private String nome;
	
	@Column(name = "cpf")
	@JsonView(Views.Public.class)
	private String cpf;
	
	@Column(name = "cnpj")
	@JsonView(Views.Public.class)
	private String cnpj;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "doc_receita_federal")
	private String documentoReceitaFederal;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private TipoPessoa tipo;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<>();
	
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
    	

}
