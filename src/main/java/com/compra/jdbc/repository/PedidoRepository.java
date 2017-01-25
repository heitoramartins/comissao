package com.compra.jdbc.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compra.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Modifying
	@Query("UPDATE Pedido as p SET p.valorTotal = ?1 WHERE p.id = ?2")
	void updateTotais(BigDecimal total, Long id);
	
		
}
