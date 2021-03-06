package com.compra.jdbc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.jdbc.dao.PedidoDAO;

@Repository
public class PedidoDaoImpl implements PedidoDAO{

	@Autowired
	private EntityManager em;
	
	@Override
	public Pedido salvar(Pedido pedido) {
		return em.merge(pedido);
	}
				
	
	@Override
	public Pedido findById(Long id) {
	
			Query vendaQuery = em.createQuery("select p from Pedido p inner join fetch p.cliente c inner join fetch p.usuario u where p.id = :id");
			vendaQuery.setParameter("id", id);
			Pedido pedido  = (Pedido) vendaQuery.getSingleResult();
			List<Item> itens = loadItens(id);
			for (Item item : itens) {
				pedido.getItens().add(item);
			}
			return pedido;
		
		
	}
	
	private List<Item> loadItens(Long id){
		Query listItens = em.createQuery("select i from Item i inner join fetch i.produto p inner join fetch p.categoria c inner join fetch i.pedido p where p.id = :id");
		listItens.setParameter("id", id);
		List<Item> itens =  listItens.getResultList();
		return itens; 
	}

	
	
	
}
