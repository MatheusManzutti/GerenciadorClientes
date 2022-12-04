package com.DevManzutti.GerenciadorClientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DevManzutti.GerenciadorClientes.entities.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long>{

}
