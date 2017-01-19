package com.compra.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compra.entity.Item;

@Repository
@Transactional
public class ItemRepository implements IItemRepository{

	
	@Autowired
	private EntityManager em;
	
	@Override
	public Item salvar(Item item){
		return em.merge(item);
	}
	
		
}
