package com.wmarvyn.sales.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmarvyn.sales.domain.Cidades;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Integer>{

}
