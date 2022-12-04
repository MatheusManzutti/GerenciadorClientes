package com.DevManzutti.GerenciadorClientes.services.exceptions;

public class ResourceNotFoundException extends RuntimeException { //definir exceção
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg); //passa a mensagem
	}
}
