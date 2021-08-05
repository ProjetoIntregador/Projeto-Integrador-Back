package org.queryjobs.queryjobs.controller;

import java.util.List;
import java.util.Optional;

import org.queryjobs.queryjobs.model.Usuario;
import org.queryjobs.queryjobs.model.UsuarioLogin;
import org.queryjobs.queryjobs.repository.UsuarioRepository;
import org.queryjobs.queryjobs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticationUsuario(@RequestBody Optional<UsuarioLogin> email) {
		return usuarioService.logarUsuario(email).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario email) {
		Optional<Usuario> novoUsuario = usuarioService.cadastrarUsuario(email);
		try {
				return ResponseEntity.ok(novoUsuario.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario email){
		Optional<Usuario> updateUsuario = usuarioService.atualizarUsuario(email);
		try {
			return ResponseEntity.ok(updateUsuario.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//Implementações
	@PutMapping("/curtir/{id}")
	public ResponseEntity<Usuario> putCurtirPostagemId (@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.curtir(id));
	}
	

	@PutMapping("/descurtir/{id}")
	public ResponseEntity<Usuario> putDescurtirPostagemId (@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.descurtir(id));
	}
	
	@GetMapping("/plus")
	public ResponseEntity<List<Usuario>> getPlus() {
		return ResponseEntity.ok(usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "curtidas")));
	}
	

}

