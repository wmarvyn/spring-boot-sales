package com.wmarvyn.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmarvyn.cursomc.domain.Categoria;

@Repository
public interface CategoriaRspository extends JpaRepository<Categoria, Integer>{

}