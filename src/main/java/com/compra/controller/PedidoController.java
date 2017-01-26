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


import com.compra.business.PedidoBusines;
import com.compra.entity.Pedido;
import com.compra.entity.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/pedido", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PedidoController {
	
	    @Autowired
	    private PedidoBusines pedidoBusiness;
	  	  	
	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity salvarOrcamento(
	        @RequestBody Pedido pedido,UriComponentsBuilder uriComponentsBuilder) {
	        Long id = pedidoBusiness.salvar(pedido);

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
	    	    pedidoBusiness.alteraOrcamento(pedido,id);
         return new ResponseEntity(OK);
	    }
	    
	    @RequestMapping(value = "/desconto/aprovado/{id}", method = RequestMethod.PUT)
	    public ResponseEntity emissaoPedido(
	            @PathVariable("id") Long id) {
	            //FIXME: Validar campos da venda
	    	pedidoBusiness.emissaoPedido(id);
        return new ResponseEntity(OK);
	    }
	    
	    @RequestMapping(value = "/desconto/reprovado/{id}", method = RequestMethod.PUT)
	    public ResponseEntity cancelamentoPedido(
	            @PathVariable("id") Long id) {
	            //FIXME: Validar campos da venda
	    	pedidoBusiness.cancelamentoPedido(id);
        return new ResponseEntity(OK);
	    }
	   	   	  	  	     
	    @JsonView(Views.Public.class)
	    @RequestMapping(value="/{id}",method = RequestMethod.GET)
		public ResponseEntity<Pedido> findById(
				@PathVariable("id") Long id)  {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoBusiness.findById(id));
		}
	  
	   

}
