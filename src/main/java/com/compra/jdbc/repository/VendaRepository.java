package com.compra.jdbc.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compra.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
	
	@Modifying
	@Query("UPDATE Venda as v SET v.valorTotal = ?1 WHERE v.id = ?2")
	void updateTotais(BigDecimal total, Long id);

}
