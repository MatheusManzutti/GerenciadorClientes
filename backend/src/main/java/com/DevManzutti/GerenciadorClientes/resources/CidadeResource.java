package com.DevManzutti.GerenciadorClientes.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DevManzutti.GerenciadorClientes.dto.CidadeDTO;
import com.DevManzutti.GerenciadorClientes.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;
	
	@GetMapping //endpoint
	public ResponseEntity<Page<CidadeDTO>> findAll(Pageable pageable) { //filtros
		Page<CidadeDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		CidadeDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CidadeDTO> insert(@RequestBody CidadeDTO dto) {
		dto = service.insert(dto); 
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") //variavel uri
				.buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto); //esqueceu
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @RequestBody CidadeDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
