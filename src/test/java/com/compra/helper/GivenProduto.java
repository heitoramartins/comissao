package com.compra.helper;


import java.math.BigDecimal;

import com.compra.entity.Produto;

public class GivenProduto {
	
	public static final long PROD_1_ID = 1L;
	public static final String PROD_1_NOME = "XBOX";
	public static final Integer PROD_1_QUANT = 05;
	public static final BigDecimal PROD_1_VLR = new BigDecimal(1600);
	
	public static final long PROD_2_ID = 1L;
	public static final String PROD_2_NOME = "GELADEIRA";
	public static final Integer PROD_2_QUANT = 05;
	public static final BigDecimal PROD_2_VLR = new BigDecimal(800);
	
	public static Produto xbox() {
        Produto produto = new Produto();
        produto.setId(PROD_1_ID);
        produto.setNome(PROD_1_NOME);
        produto.setQuantidadeEstoque(PROD_1_QUANT);
        produto.setVlrUnitario(PROD_1_VLR);
        return produto;
    }
	
	public static Produto geladeira() {
        Produto produto = new Produto();
        produto.setId(PROD_2_ID);
        produto.setNome(PROD_2_NOME);
        produto.setQuantidadeEstoque(PROD_2_QUANT);
        produto.setVlrUnitario(PROD_2_VLR);
        return produto;
    }

}
