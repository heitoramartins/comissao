package com.compra.jdbc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.compra.entity.Item;
import com.compra.jdbc.dao.ItemDAO;

@Repository
public class ItemDaoImpl implements ItemDAO{

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Item> loadItensByPedido(Long id){
		Query listItens = em.createQuery("select i from Item i inner join fetch i.produto p inner join fetch p.categoria c inner join fetch i.pedido p where p.id = :id");
		listItens.setParameter("id", id);
		List<Item> itens =  listItens.getResultList();
		return itens; 
	}
}
