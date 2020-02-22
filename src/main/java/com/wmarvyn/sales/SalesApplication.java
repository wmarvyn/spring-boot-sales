package com.wmarvyn.sales;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.wmarvyn.sales.domain.*;
import com.wmarvyn.sales.domain.enums.EstadoPagamento;
import com.wmarvyn.sales.domain.enums.TipoCliente;
import com.wmarvyn.sales.repositores.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SalesApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRspository;
	@Autowired
	private ProdutoRepository produtoRspository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private itemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escrotorio");
		
		Produto p1 = new Produto(null,"Computador",20000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRspository.save(cat1);
		categoriaRspository.save(cat2);
		
		produtoRspository.save(p1);
		produtoRspository.save(p2);
		produtoRspository.save(p3);
		
		Estado est1 = new Estado(null, "Minas Geraes");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidades c1 = new Cidades(null, "Uberlandia", est1);
		Cidades c2 = new Cidades(null, "São Paulo", est2);
		Cidades c3 = new Cidades(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(est1);
		estadoRepository.save(est2);
		
		cidadeRepository.save(c1);
		cidadeRepository.save(c2);
		cidadeRepository.save(c3);
		
		
		Cliente cli1 = new Cliente(null, "Maria teste", "emailteste@gmail.com", "00000000000001", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111111","22222222"));
		
	
		Endereco e1 = new Endereco(null,"rua teste", "100", "frente", "jardin","0000000", cli1,c1);
		Endereco e2 = new Endereco(null,"rua teste2", "101", "frente", "jardin","0000001", cli1,c2);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2019 10:00"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("30/10/2019 10:00"),cli1,e2);

		Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagt1);

		Pagamento pagt2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("30/09/2019 00:00"),null);
		ped2.setPagamento(pagt2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));

		pagamentoRepository.saveAll(Arrays.asList(pagt1,pagt2));
			
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,800.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
			

	}

}
