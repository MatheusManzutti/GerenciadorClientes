package com.DevManzutti.GerenciadorClientes.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.DevManzutti.GerenciadorClientes.entities.Cidade;
import com.DevManzutti.GerenciadorClientes.entities.Clientes;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cidade;
	private String uf;
	
	private List<ClientesDTO> clientes = new ArrayList<>();
	
	public CidadeDTO() {
	}
	
	public CidadeDTO(Long id, String cidade, String uf) { //construtor com argumentos que pega as referências desta classe
		this.id = id;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	public CidadeDTO(Cidade entity) { //construtor que pega as referências da entidade que passara para esta classe
		this.id = entity.getId();
		this.cidade = entity.getCidade();
		this.uf = entity.getUf();
	}
	
	public CidadeDTO(Cidade entity, List<Clientes> clientes) { //Vou adicionar um novo cliente para minha entidade cidade.
		this(entity);
		clientes.forEach(cli -> this.clientes.add(new ClientesDTO(cli)));
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
}
