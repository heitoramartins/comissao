package com.compra.jdbc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.compra.entity.Item;
import com.compra.entity.Venda;
import com.compra.jdbc.dao.VendaDAO;

@Repository
public class VendaDaoImpl implements VendaDAO{

	@Autowired
	private EntityManager em;
	
	@Override
	public Venda salvar(Venda venda) {
		return em.merge(venda);
	}
	
	@Override
	public List<Venda> findAll() {
		Query listVendas = em.createQuery("select v from Venda v ");
		List<Venda> vendas = listVendas.getResultList();
	 	return vendas;
	}
		
	@Override
	public List<Venda> findVendasById(Long id) {
		Query listVendas = em.createQuery("SELECT v FROM Venda v inner join fetch v.cliente c inner join fetch v.funcionario f where v.id = :id");
		listVendas.setParameter("id", id);
		List<Venda> vendas = listVendas.getResultList();
		
	   for (Venda venda : vendas) {
		    List<Item> itens = loadItens(venda.getId());
		    venda.setItens(itens);
		}
	 	return vendas;
	}
	
	private List<Item> loadItens(Long id){
		Query listItens = em.createQuery("select i from Item i inner join fetch i.produto p inner join i.venda v where v.id = :id");
		listItens.setParameter("id", id);
		List<Item> itens =  listItens.getResultList();
		return itens; 
	}
	
	
}
