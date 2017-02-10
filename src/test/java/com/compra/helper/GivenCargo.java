package com.compra.helper;

import com.compra.entity.Cargo;

public class GivenCargo {
	
	public static final String NOME = "Vendedor";

	public static Cargo vendedor() {
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome(NOME);
        return cargo;
    }
}
