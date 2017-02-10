package com.compra.helper;

import java.math.BigDecimal;

import com.compra.entity.Usuario;

public class GivenUsuario {
	
	public static final long USU_1_ID = 1L;
	public static final String USU_1_NOME = "Juliano";
	public static final BigDecimal USU_1_SALARIO = new BigDecimal(5000);
	
	public static final long USU_2_ID = 1L;
	public static final String USU_2_NOME = "Maria";
	public static final BigDecimal USU_2_SALARIO = new BigDecimal(5000);

	public static Usuario juliano() {
       Usuario usuario = new Usuario();
       usuario.setCargo(GivenCargo.vendedor());
       usuario.setId(USU_1_ID);
       usuario.setNome(USU_1_NOME);
       usuario.setSalario(USU_1_SALARIO);
       return usuario;
    }
	
	public static Usuario maria() {
	       Usuario usuario = new Usuario();
	       usuario.setCargo(GivenCargo.vendedor());
	       usuario.setId(USU_2_ID);
	       usuario.setNome(USU_2_NOME);
	       usuario.setSalario(USU_2_SALARIO);
	       return usuario;
	    }
	
}
