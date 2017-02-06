package com.compra.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.compra.business.exception.EmailPedidoNotCreateException;
import com.compra.entity.Cliente;
import com.compra.entity.Item;
import com.compra.entity.Pedido;
import com.compra.entity.Produto;
import com.compra.jdbc.repository.ClienteRepository;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.vo.PedidoVO;

@Component
public class PedidoEmailService{
		
	@Autowired
    private VelocityEngine velocityEngine;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
			
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviar(Object object) {
	  Pedido pedido = (Pedido)object;
	  MimeMessagePreparator preparator = getMessagePreparator(pedido);
	    try {
	    	 //FIXME: Adicionar Log INFO
	    	javaMailSender.send(preparator);
	        }
	        catch (MailException ex) {
	        	throw new EmailPedidoNotCreateException(" não foi possivel enviar o email! "+ex.getMessage());
	        }
	    }
	    
	  private MimeMessagePreparator getMessagePreparator(final Pedido pedido){
	            MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            	            
		       
	    	    @Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
					    
					    Pedido p = pedidoRepository.findOne(pedido.getId());
		                PedidoVO pedidoVO = criaPedidoVO(p);
					    
					    helper.setSubject("Orcamento do pedido: "+pedido.getId());
		                helper.setFrom("tobiasarauro@gmail.com");
		                helper.setTo(pedidoVO.getCliente().getEmail());
		             	               
		                Map<String, Object> model = new HashMap<String, Object>();
		                model.put("pedido", pedidoVO);
		                model.put("numberTool", new NumberTool());
		                model.put("locale", new Locale("pt", "BR"));
		                 
		                String text = geVelocityTemplateContent(model);
		                helper.setText(text, true);
		       }
			};
			return preparator; 
	 	         
	    }
	 	  
	@SuppressWarnings("deprecation")
	public String geVelocityTemplateContent(Map<String, Object> model){
	        StringBuffer content = new StringBuffer();
	        try{
	            content.append( VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "pedido.vm", "UTF-8", model));
	            return content.toString();
	        }catch(Exception e){
	        	//FIXME: Adicionar Log ERROR
	        	throw new EmailPedidoNotCreateException(" não foi possivel criar o template! "+e.getMessage());
	        }
	    }

	
	
	private PedidoVO criaPedidoVO(Pedido pedido){
	
		PedidoVO pedidoVO = new PedidoVO();
		List<Item> itens = new ArrayList<>();
		Item item = null;
		for (Item i : pedido.getItens()) {
			 item = new Item();
			 Produto produto = produtoRepository.findOne(i.getProduto().getId());   
			 item.setProduto(produto);
			 item.setQuantidade(i.getQuantidade());
			 item.setValorTotal(i.getValorTotal());
			 itens.add(item);
		}
		for (Item i: itens) {
			pedidoVO.getItens().add(i);
		}
		Cliente cliente = clienteRepository.findOne(pedido.getCliente().getId());
		pedidoVO.setCliente(cliente);
		pedidoVO.setValorTotal(pedido.getValorTotal());
		pedidoVO.setValorFrete(pedido.getValorFrete());
		pedidoVO.setValorDesconto(pedido.getValorDesconto());
		pedidoVO.setStatus(pedido.getStatus());
    	pedidoVO.setFormaPagamento(pedido.getFormaPagamento());
	  
		return pedidoVO;
	}
	 
		  
}	
	

