package com.DevManzutti.GerenciadorClientes.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.DevManzutti.GerenciadorClientes.services.exceptions.DataBaseException;
import com.DevManzutti.GerenciadorClientes.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //intercepta a exceção dos serviços
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //tratar a exceção / .class tipo de exceção vai interceptar
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		//StandardError resposta da requisição / HttpServlet objeto contém as info da requisição
		HttpStatus status = HttpStatus.NOT_FOUND; //declarando o http
		StandardError err = new StandardError();
		
		//definindo os dados
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resources not found");
		err.setMessage(e.getMessage()); //mensagem customizada dos serviços
		err.setPath(request.getRequestURI()); //caminho da requisição
		return ResponseEntity.status(status).body(err); //.status permite que customizo o status
	}
	
	//violação de integridade
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST; //cod 400
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
