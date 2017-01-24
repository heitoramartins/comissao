package com.compra.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compra.entity.Cliente;
import com.compra.entity.Produto;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
