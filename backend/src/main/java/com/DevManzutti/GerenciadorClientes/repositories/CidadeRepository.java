package com.DevManzutti.GerenciadorClientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DevManzutti.GerenciadorClientes.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
