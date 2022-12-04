package com.DevManzutti.GerenciadorClientes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DevManzutti.GerenciadorClientes.dto.ClientesDTO;
import com.DevManzutti.GerenciadorClientes.entities.Clientes;
import com.DevManzutti.GerenciadorClientes.repositories.ClientesRepository;
import com.DevManzutti.GerenciadorClientes.services.exceptions.DataBaseException;
import com.DevManzutti.GerenciadorClientes.services.exceptions.ResourceNotFoundException;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientesDTO> findAllPaged(Pageable pageable) {
		Page<Clientes> list = repository.findAll(pageable);
		return list.map(x -> new ClientesDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientesDTO findById(Long id) {
		Optional<Clientes> obj = repository.findById(id);
		Clientes entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new ClientesDTO(entity);
	}

	@Transactional
	public ClientesDTO insert(ClientesDTO dto) {
		Clientes entity = new Clientes();
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setCnpj(dto.getCnpj());
		entity.setEndereco(dto.getEndereco());
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setTel(dto.getTel());
		entity.setEmail(dto.getEmail());
		entity = repository.save(entity);
		return new ClientesDTO(entity);
	}

	@Transactional
	public ClientesDTO update(Long id, ClientesDTO dto) {
		try {
			Clientes entity = repository.getById(id);
			entity.setNome(dto.getNome());
			entity.setCpf(dto.getCpf());
			entity.setCnpj(dto.getCnpj());
			entity.setEndereco(dto.getEndereco());
			entity.setBairro(dto.getBairro());
			entity.setCep(dto.getCep());
			entity.setTel(dto.getTel());
			entity.setEmail(dto.getEmail());
			entity = repository.save(entity);
			return new ClientesDTO(entity);
		}
		catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrety violation");
		}
	}
}
