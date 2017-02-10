package com.compra.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.compra.builder.PedidoBuider;
import com.compra.business.exception.PedidoNotFoundException;
import com.compra.descontos.GerarDescontos;
import com.compra.entity.Pedido;
import com.compra.entity.enums.FormaPagamento;
import com.compra.helper.GivenCliente;
import com.compra.helper.GivenEnderecoEntrega;
import com.compra.helper.GivenItem;
import com.compra.helper.GivenUsuario;
import com.compra.jdbc.dao.PedidoDAO;
import com.compra.jdbc.repository.ItemRepository;
import com.compra.jdbc.repository.PedidoRepository;
import com.compra.jdbc.repository.ProdutoRepository;
import com.compra.status.GerarPedido;



public class PedidoServiceTest {
	
	@InjectMocks
	private PedidoService service;
	
	@Mock
	private PedidoDAO pedidoDAO;
	@Mock
	private PedidoRepository pedidoRepository;
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private ProdutoRepository produtoRepository;
	@Mock
	private GerarPedido gerarPedido;
	@Mock
	private GerarDescontos gerarDescontos;
	@Mock
	private AcoesAposGerarPedido acao1;
	@Mock
	private AcoesAposGerarPedido acao2;
	@Mock
	private List<AcoesAposGerarPedido> acoes;
	
	private BigDecimal valorFrete = new BigDecimal(20);
	
	
	@Before
	public void criaPedido() {
	    MockitoAnnotations.initMocks(this);
		this.acao1 = mock(AcoesAposGerarPedido.class);
		this.acao2 = mock(AcoesAposGerarPedido.class);
		this.acoes = Arrays.asList(acao1, acao2);
				
	}
	
	@Test
	public void deveListarTodosOsPedidosCadastrados(){
		
	   Pedido pedido = new PedidoBuider()
			   .idPedido(1L)
			   .dataPedido(LocalDateTime.now())
			   .paraCliente(GivenCliente.heitor())
			   .paraUsuario(GivenUsuario.juliano())
			   .formaDePagamento(FormaPagamento.DINHEIRO)
			   .valorDoFrete(valorFrete)
			   .enderecoEntrega(GivenEnderecoEntrega
					   .enderecoEntrega())
			   .adicionarItem(GivenItem.xbox())
			   .buscaPedido();
	   
	   when(pedidoDAO.findById(1L)).thenReturn(pedido);
	   Pedido aux = service.findById(pedido.getId());
	   Assert.assertNotNull(aux);
	
	}
	
	@Test(expected=PedidoNotFoundException.class)
	public void deveRetornarExceptionParaUmPedidoInexistente(){
			Pedido pedido = new Pedido();	
			when(pedidoDAO.findById(1L)).thenReturn(pedido);
			Pedido aux = service.findById(pedido.getId());
			Assert.assertNotNull(aux);
	}
		
	/*@Test
	public void deveSalvarUmPedidoNaBase(){
		 Pedido pedido = new PedidoBuider()
				   .idPedido(1L)
				   .dataPedido(LocalDateTime.now())
				   .paraCliente(GivenCliente.heitor())
				   .paraUsuario(GivenUsuario.juliano())
				   .formaDePagamento(FormaPagamento.DINHEIRO)
				   .valorDoFrete(valorFrete)
				   .enderecoEntrega(GivenEnderecoEntrega
						   .enderecoEntrega())
				   .adicionarItem(GivenItem.xbox())
				   .buscaPedidoValidaLista();
			
		  when(pedidoRepository.save(pedido)).thenReturn(pedido);
		  when(produtoRepository.findOne(anyLong())).thenReturn(GivenProduto.xbox());
		  when(itemRepository.save(GivenItem.xbox()));
		  when(pedidoRepository.findOne(anyLong())).thenReturn(pedido);
		  
		   Long aux = service.salvarOrcamento(pedido);
		    Assert.assertNotNull(aux);
		  
	 }*/
	
	

	
	 
}
