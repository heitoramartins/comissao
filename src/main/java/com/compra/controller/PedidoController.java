package com.compra.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.compra.business.VendaBusines;
import com.compra.entity.Pedido;
import com.compra.entity.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/pedido", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PedidoController {
	
	    @Autowired
	    private VendaBusines vendaBusiness;
	  	  	
	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity salvarOrcamento(
	        @RequestBody Pedido pedido,UriComponentsBuilder uriComponentsBuilder) {
	        Long id = vendaBusiness.salvar(pedido);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(uriComponentsBuilder
	                .path("/pedido")
	                .buildAndExpand(pedido, id)
	                .toUri());
	        return new ResponseEntity(headers, CREATED);
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public ResponseEntity alterarOrcamento(
	            @PathVariable("id") Long id,
	            @RequestBody Pedido pedido) {
	            //FIXME: Validar campos da venda
	    	    vendaBusiness.alteraOrcamento(pedido,id);
         return new ResponseEntity(OK);
	    }
	    
	    
	    @JsonView(Views.Public.class)
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ResponseEntity listaVendas
	           (@PathVariable("id") Long id) {
	        List<Pedido> pedidos = vendaBusiness.findVendasById(id);
	        return new ResponseEntity(pedidos, OK);
	    }
	     
	    @JsonView(Views.Public.class)
	    @RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Pedido>> listarTodas() {
			return ResponseEntity.status(HttpStatus.OK).body(vendaBusiness.findAll());
		}
	  
	   

}
