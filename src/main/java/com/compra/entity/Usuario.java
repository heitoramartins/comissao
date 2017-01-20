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
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "nome")
	@JsonView(Views.Public.class)
	private String nome;
	
	@Column(name = "salario")
    private BigDecimal salario;
	
	@ManyToOne
	@JoinColumn(name = "fk_cargo")
    private Cargo cargo;
	
	@Column(name = "email",length = 255)
	private String email;
	
	@Column(name = "senha",length = 80)
	private String senha;
         
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
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
      	

}
