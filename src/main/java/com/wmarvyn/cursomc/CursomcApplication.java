package com.wmarvyn.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wmarvyn.cursomc.domain.Categoria;
import com.wmarvyn.cursomc.domain.Cidades;
import com.wmarvyn.cursomc.domain.Cliente;
import com.wmarvyn.cursomc.domain.Endereco;
import com.wmarvyn.cursomc.domain.Estado;
import com.wmarvyn.cursomc.domain.Produto;
import com.wmarvyn.cursomc.domain.enums.TipoCliente;
import com.wmarvyn.cursomc.repositores.CategoriaRepository;
import com.wmarvyn.cursomc.repositores.CidadeRepository;
import com.wmarvyn.cursomc.repositores.ClienteRepository;
import com.wmarvyn.cursomc.repositores.EnderecoRepository;
import com.wmarvyn.cursomc.repositores.EstadoRepository;
import com.wmarvyn.cursomc.repositores.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
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
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
