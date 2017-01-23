package com.compra.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compra.business.VendaBusines;
import com.compra.entity.Pedido;

@RestController
@RequestMapping(value = "/desconto", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DescontoController {
	
	
	    @Autowired
	    private VendaBusines vendaBusiness;
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public ResponseEntity alterarOrcamento(
	            @PathVariable("id") Long id,
	            @RequestBody Pedido pedido) {
	            //FIXME: Validar campos da venda
	    	    vendaBusiness.alteraOrcamento(pedido,id);
         return new ResponseEntity(OK);
	    }

}
