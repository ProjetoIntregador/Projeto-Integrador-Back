package org.queryjobs.queryjobs.controller;

import java.util.List;
import org.queryjobs.queryjobs.model.Produto;
import org.queryjobs.queryjobs.repository.ProdutoRepository;
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
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	
	@Autowired
	private ProdutoRepository produtorepository;

	@GetMapping
	public HttpEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(produtorepository.findAll());
	} 
    
	@GetMapping ("/{id}") 
	public ResponseEntity<Produto>  GetById(@PathVariable Long id){
		return produtorepository.findById(id)
			.map(resp -> ResponseEntity.ok (resp))
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/especificacao/{especificacao}")
	public ResponseEntity<List<Produto>> GetByEspecificacao(@PathVariable String especificacao) {
		return ResponseEntity.ok(produtorepository.findAllByEspecificacaoContainingIgnoreCase(especificacao));
	}

	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtorepository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> put(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(produtorepository.save(produto));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		produtorepository.deleteById(id);
	}
}
