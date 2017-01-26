package com.compra.jdbc.dao;

import java.util.List;

import com.compra.entity.Item;

public interface ItemDAO {
	 List<Item> loadItensByPedido(Long id);
}
