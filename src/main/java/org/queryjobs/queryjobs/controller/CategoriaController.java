package org.queryjobs.queryjobs.controller;

import java.util.List;

import org.queryjobs.queryjobs.model.Categoria;
import org.queryjobs.queryjobs.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public HttpEntity<List<Categoria>> GetAll() {
		return ResponseEntity.ok(repository.findAll()); // OK = 200 }
	} // Aqui é onde de fato define o método que foi assinado lá no repositório.

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/tipotrabalho/{tipotrabalho}")
	public ResponseEntity<List<Categoria>> GetByTipo_trabalho(@PathVariable String tipotrabalho) {
		return ResponseEntity.ok(repository.findAllByTipotrabalhoContainingIgnoreCase(tipotrabalho));
	}

	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria tipotrabalho) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tipotrabalho));

	}

	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria tipotrabalho) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tipotrabalho));

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);

	}
}
