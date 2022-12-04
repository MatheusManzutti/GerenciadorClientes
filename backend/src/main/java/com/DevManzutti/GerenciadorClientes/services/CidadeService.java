package com.DevManzutti.GerenciadorClientes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DevManzutti.GerenciadorClientes.dto.CidadeDTO;
import com.DevManzutti.GerenciadorClientes.entities.Cidade;
import com.DevManzutti.GerenciadorClientes.repositories.CidadeRepository;
import com.DevManzutti.GerenciadorClientes.services.exceptions.DataBaseException;
import com.DevManzutti.GerenciadorClientes.services.exceptions.ResourceNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repository;
	
	@Transactional(readOnly = true) //evita fazer o bloqueio no banco na trasação. É somente leitura.
	public Page<CidadeDTO> findAllPaged(Pageable pageable) {
		Page<Cidade> list = repository.findAll(pageable);
		return list.map(x -> new CidadeDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CidadeDTO findById(Long id) {
		Optional<Cidade> obj = repository.findById(id); //Optional evita trabalhar com o valor NULL, no qual vai buscar no repository
		Cidade entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found")); //Quando busca o id no meu reposity, e caso não exista o id, é lançado uma exceção
		return new CidadeDTO(entity);
	}
	
	@Transactional
	public CidadeDTO insert(CidadeDTO dto) {
		Cidade entity = new Cidade();
		entity.setCidade(dto.getCidade());
		entity.setUf(dto.getUf());
		entity = repository.save(entity);
		return new CidadeDTO(entity);
	}
	
	@Transactional
	public CidadeDTO update(Long id, CidadeDTO dto) {
		try {
			Cidade entity = repository.getById(id); //getById não toca no banco
			entity.setCidade(dto.getCidade());
			entity.setUf(dto.getUf());
			entity = repository.save(entity); //salva minha entidade
			return new CidadeDTO(entity);
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
