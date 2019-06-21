package com.wmarvyn.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wmarvyn.cursomc.domain.Categoria;
import com.wmarvyn.cursomc.domain.Produto;
import com.wmarvyn.cursomc.repositores.CategoriaRspository;
import com.wmarvyn.cursomc.repositores.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRspository categoriaRspository;
	
	@Autowired
	private ProdutoRepository produtoRspository;
	
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
		
	}

}
