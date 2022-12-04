package com.DevManzutti.GerenciadorClientes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.DevManzutti.GerenciadorClientes.entities.Clientes;

public class ClientesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
//	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;
	
	@NotBlank(message = "Campo requerido")
	private String cpf;
	
	@NotBlank(message = "Campo requerido")
	private String cnpj;
	
	private String endereco;
	private String bairro;
	private String cep;
	private String tel;
	private String email;
	
	public ClientesDTO() {
	}
	
	public ClientesDTO (Long id, String nome, String cpf, String cnpj, String endereco, String bairro, String cep,
			String tel, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.tel = tel;
		this.email = email;
	}
	
	public ClientesDTO(Clientes entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.cnpj = entity.getCnpj();
		this.endereco = entity.getEndereco();
		this.bairro = entity.getBairro();
		this.cep = entity.getCep();
		this.tel = entity.getTel();
		this.email = entity.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
