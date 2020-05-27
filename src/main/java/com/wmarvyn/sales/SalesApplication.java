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


	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

			

	}

}
