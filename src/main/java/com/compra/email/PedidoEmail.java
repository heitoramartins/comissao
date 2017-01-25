package com.compra.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.compra.entity.Cliente;
import com.compra.entity.Pedido;
import com.compra.jdbc.repository.ClienteRepository;

@Component
public class PedidoEmail implements MailService{
		
	@Autowired
    private VelocityEngine velocityEngine;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	  @Override
	  public void sendEmail(Object object) {
	  Pedido pedido = (Pedido)object;
	  MimeMessagePreparator preparator = getMessagePreparator(pedido);
	    try {
	    	 //FIXME: Adicionar Log INFO
	    	javaMailSender.send(preparator);
	        }
	        catch (MailException ex) {
	        	throw new EmailPedidoNotCreateException("Não foi possivel enviar o email!"+ex.getMessage());
	        }
	    }
	    
	  private MimeMessagePreparator getMessagePreparator(final Pedido pedido){
	            MimeMessagePreparator preparator = new MimeMessagePreparator() {
		       
	    	    @Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
					    helper.setSubject("Orcamento do pedido: "+pedido.getId());
		                helper.setFrom("tobiasarauro@gmail.com");
		                Cliente cliente = clienteRepository.findOne(pedido.getCliente().getId());
		                helper.setTo(cliente.getEmail());
		       
		                Map<String, Object> model = new HashMap<String, Object>();
		                model.put("pedido", pedido);
		                 
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
	        	throw new EmailPedidoNotCreateException("Não foi possivel criar o template!"+e.getMessage());
	        }
	    }
	  
}	
	

