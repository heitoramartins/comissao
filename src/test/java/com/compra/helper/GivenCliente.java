package com.compra.helper;

import java.util.ArrayList;
import java.util.List;

import com.compra.entity.Cliente;
import com.compra.entity.Endereco;
import com.compra.entity.enums.TipoPessoa;

public class GivenCliente {
	    public static final String CEP = "02038030";
	    public static final String CIDADE = "Sao Paulo";
	    public static final String COMPLEMENTO = "ap 46";
	    public static final String LOGRADOURO = "rua feliz";
	    public static final String NUMERO = "25";
	    public static final String UF = "SP";
	
	    public static final  String CNPJ_1_CLI = "";
	    public static final String CPF_1_CLI = "98175487548";
	    public static final String DOC_1_CLI = "AGT76";
	    public static final String EMAIL_1_CLI = "joao@j";
	    public static final long ID_1_CLI = 1L;
	    public static final String NOME_1_CLI = "Joao Paulo";
	    public static final TipoPessoa TIPO_1_CLI = TipoPessoa.FISICA;
	    
	    public static final  String CNPJ_2_CLI = "";
	    public static final String CPF_2_CLI = "98175487542";
	    public static final String DOC_2_CLI = "AGT75";
	    public static final String EMAIL_2_CLI = "heitor@j";
	    public static final long ID_2_CLI = 1L;
	    public static final String NOME_2_CLI = "Heitor Araujo";
	    public static final TipoPessoa TIPO_2_CLI = TipoPessoa.JURIDICA;
	
	 public static Cliente Joao() {
	        Cliente cliente = new Cliente();
	        List<Endereco> enderecos = new ArrayList<>();
	        cliente.setCnpj(CNPJ_1_CLI);
	        cliente.setCpf(CPF_1_CLI);
	        cliente.setDocumentoReceitaFederal(DOC_1_CLI);
	        cliente.setEmail(EMAIL_1_CLI);
	        enderecos.add(endereco());
	        cliente.setEnderecos(enderecos);
	        cliente.setId(ID_1_CLI);
	        cliente.setNome(NOME_1_CLI);
	        cliente.setTipo(TIPO_1_CLI);
	        return cliente;
	   }
	 
	 public static Cliente heitor() {
	        Cliente cliente = new Cliente();
	        List<Endereco> enderecos = new ArrayList<>();
	        cliente.setCnpj(CNPJ_2_CLI);
	        cliente.setCpf(CPF_2_CLI);
	        cliente.setDocumentoReceitaFederal(DOC_2_CLI);
	        cliente.setEmail(EMAIL_2_CLI);
	        enderecos.add(endereco());
	        cliente.setEnderecos(enderecos);
	        cliente.setId(ID_2_CLI);
	        cliente.setNome(NOME_2_CLI);
	        cliente.setTipo(TIPO_2_CLI);
	        return cliente;
	   }
	 
	 public static Endereco endereco(){
		    Endereco endereco = new Endereco();
		    endereco.setCep(CEP);
		    endereco.setCidade(CIDADE);
		    endereco.setComplemento(COMPLEMENTO);
		    endereco.setLogradouro(LOGRADOURO);
		    endereco.setNumero(NUMERO);
		    endereco.setUf(UF);
		    return endereco;
	 }

}
