package com.compra.helper;

import com.compra.entity.EnderecoEntrega;

public class GivenEnderecoEntrega {
	
	    public static final String CEP = "02038030";
	    public static final String CIDADE = "Sao Paulo";
	    public static final String COMPLEMENTO = "ap 46";
	    public static final String LOGRADOURO = "rua feliz";
	    public static final String NUMERO = "25";
	    public static final String UF = "SP";
	   
	   
	
	 public static EnderecoEntrega enderecoEntrega() {
	        EnderecoEntrega entrega = new EnderecoEntrega();
	       	entrega.setCep(CEP);
	       	entrega.setCidade(CIDADE);
	       	entrega.setComplemento(COMPLEMENTO);
	       	entrega.setLogradouro(LOGRADOURO);
	       	entrega.setNumero(NUMERO);
	       	entrega.setUf(UF);
			return entrega;
	   }


}
