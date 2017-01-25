package com.compra.service;

import java.util.HashMap;
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
import com.compra.entity.Pedido;

@Component
public class PedidoEmailService{
		
	@Autowired
    private VelocityEngine velocityEngine;
	
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
					    helper.setSubject("Orcamento do pedido: "+pedido.getId());
		                helper.setFrom("tobiasarauro@gmail.com");
		                helper.setTo("heitoramartins@gmail.com");
		       
		                Map<String, Object> model = new HashMap<String, Object>();
		                model.put("pedido", pedido);
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

		  
}	
	

