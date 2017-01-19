package com.compra.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.compra.converter.CustomLocalDateTimeDeserialize;
import com.compra.converter.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "venda")
public class Venda {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name = "fk_cliente")
	@JsonView(Views.Public.class)
	private Cliente cliente;
	
	@JsonView(Views.Public.class)
	@OneToMany(mappedBy = "venda", targetEntity = Item.class, fetch = FetchType.LAZY)
	private List<Item> itens;
	
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserialize.class)
    private LocalDateTime data;
	
	@ManyToOne(targetEntity = Funcionario.class)
	@JoinColumn(name = "fk_funcionario")
	@JsonView(Views.Public.class)
	private Funcionario funcionario;
	
	@Column(name = "total")
	@JsonView(Views.Public.class)
	private Double total;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
			
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	@PrePersist
    public void prePersist() {
        this.data = LocalDateTime.now();
    }
	
	@Transient
	public void calcularTotais(){
	  	for (Item item : itens) {
			this.total += item.getQuantidade() * item.getProduto().getVlrUnitario();
		}
	}
	
}
