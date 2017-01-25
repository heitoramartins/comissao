package com.compra.entity;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.compra.entity.enums.FormaPagamento;
import com.compra.entity.enums.StatusDesconto;
import com.compra.entity.enums.StatusPedido;
import com.compra.status.desconto.Desconto;
import com.compra.status.desconto.EmAprovacao;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Public.class)
	private Long id;
	
	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name = "fk_cliente")
	@JsonView(Views.Public.class)
	private Cliente cliente;
	
	@JsonView(Views.Public.class)
	@OneToMany(mappedBy = "pedido", targetEntity = Item.class, fetch = FetchType.LAZY)
	private List<Item> itens;
	
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserialize.class)
	@JsonView(Views.Public.class)
    private LocalDateTime dataVenda;
	
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserialize.class)
	@JsonView(Views.Public.class)
    private LocalDateTime dataEntrega;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "fk_funcionario")
	@JsonView(Views.Public.class)
	private Usuario usuario;
	
	@Column(name = "vlr_total")
	@JsonView(Views.Public.class)
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@Column(name = "vlr_frete")
	@JsonView(Views.Public.class)
	private BigDecimal valorFrete = BigDecimal.ZERO;
	
	@Column(name = "vlr_desconto")
	@JsonView(Views.Public.class)
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	private StatusPedido status = StatusPedido.ORCAMENTO;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Public.class)
	@Column(name = "status_desconto")
	private StatusDesconto statusDesconto = StatusDesconto.EM_APROVACAO;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento",length = 20)
	@JsonView(Views.Public.class)
	private FormaPagamento formaPagamento;
	
	@Embedded
	@JsonView(Views.Public.class)
	private EnderecoEntrega enderecoEntrega;
	
	@Transient
	public Desconto desconto;
	
	public Pedido() {
	    desconto = new EmAprovacao();
	}
		
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
		
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getFuncionario() {
		return usuario;
	}
	public void setFuncionario(Usuario usuario) {
		this.usuario = usuario;
	}
		
	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
			
	public StatusDesconto getStatusDesconto() {
		return statusDesconto;
	}
	public void setStatusDesconto(StatusDesconto statusDesconto) {
		this.statusDesconto = statusDesconto;
	}
	@Transient
	public boolean isNovo(Pedido pedido){
		return pedido.getId() == null;
	}
			
	@Transient
	public BigDecimal calculaFreteMaisDescontoExtraAprovado(Pedido pedido, BigDecimal total){
		   BigDecimal descontoAtual = BigDecimal.ZERO;
		   BigDecimal descontoAnterior = BigDecimal.ZERO;
		   descontoAnterior = pedido.getValorDesconto();
		   pedido.aplicaDescontoExtra();
		   pedido.aprovar();
		   descontoAtual = pedido.aplicaDescontoExtra();
		   pedido.setValorDesconto(descontoAnterior.add(descontoAtual));
		   total = total.subtract(pedido.getValorDesconto()).add(pedido.getValorFrete());
		   return total.setScale(2, RoundingMode.UP);//arredondas casas decimais
	}
				
	@Transient //desconto de 5% na primeira compra
	public BigDecimal calculaFreteMaisDesconto(Pedido pedido, BigDecimal total){
		   BigDecimal desconto = BigDecimal.ZERO;
		   desconto = total.multiply(new BigDecimal(0.05)).setScale(2, RoundingMode.UP);;
		   pedido.setValorDesconto(desconto);
		   total = total.subtract(desconto).add(pedido.getValorFrete());
		   return total.setScale(2, RoundingMode.UP);//arredondas casas decimais
	}
	
	@Transient
	public boolean isValorMenorQueZero(BigDecimal valor){
		return valor.compareTo(BigDecimal.ZERO) < 0;
	}
	 
	 @Transient
	 public BigDecimal aplicaDescontoExtra(){
		return desconto.aplicarDescontoExtra(this);
	 }
	
	 @Transient
	 public void aprovar(){
		 desconto.aprovar(this);
	 }
	 @Transient
	 public void reprovar(){
		 desconto.reprovar(this);
	 }
	 @Transient
	 public void finaliza() {
		 desconto.finaliza(this); 
	 }
			
	@PrePersist
    public void prePersist() {
        this.dataVenda = LocalDateTime.now();
        
    }
						
}
