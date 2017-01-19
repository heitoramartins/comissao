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
import com.compra.entity.Venda;
import com.compra.entity.Views;
import com.compra.service.VendaService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/venda", produces = { MediaType.APPLICATION_JSON_VALUE })
public class VendaController {
	
	    @Autowired
	    private VendaBusines vendaBusiness;
	    
	    @Autowired
	    private VendaService vendaService;
	
	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity criarVenda(
	        @RequestBody Venda venda,UriComponentsBuilder uriComponentsBuilder) {
	        Long id = vendaBusiness.salvar(venda);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(uriComponentsBuilder
	                .path("/venda")
	                .buildAndExpand(venda, id)
	                .toUri());
	        return new ResponseEntity(headers, CREATED);
	    }
	    
	    @JsonView(Views.Public.class)
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ResponseEntity listaVendas
	           (@PathVariable("id") Long id) {
	        List<Venda> vendas = vendaBusiness.findVendasById(id);
	        return new ResponseEntity(vendas, OK);
	    }
	     
	    @JsonView(Views.Public.class)
	    @RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Venda>> listarTodas() {
			return ResponseEntity.status(HttpStatus.OK).body(vendaBusiness.findAll());
		}
	  
	   

}
