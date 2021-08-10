package org.queryjobs.queryjobs.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.queryjobs.queryjobs.model.Usuario;
import org.queryjobs.queryjobs.model.UsuarioLogin;
import org.queryjobs.queryjobs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;




@Service
public class UsuarioService {
	

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario email) {
		
		if(usuarioRepository.findByEmail(email.getEmail()).isPresent())
			throw new ResponseStatusException(
			          	HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
	
		int datanascimento = Period.between(email.getDatanascimento(), LocalDate.now()).getYears();
		
		if(datanascimento < 18)
			throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
			
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(email.getSenha());
		email.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(email));
	}

	
	public Optional<Usuario> atualizarUsuario(Usuario email){
		
		if(usuarioRepository.findById(email.getId()).isPresent()) {
			
			int datanascimento = Period.between(email.getDatanascimento(), LocalDate.now()).getYears();
			
			if(datanascimento < 18)
				throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(email.getSenha());
			email.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(email));
		
		}else {
			
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			
		}
		
	}
	
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> email = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

		if (email.isPresent()) {
			if (encoder.matches(usuarioLogin.get().getSenha(), email.get().getSenha())) {

				String auth = usuarioLogin.get().getEmail() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioLogin.get().setToken(authHeader);				
				usuarioLogin.get().setNomeusuario(email.get().getNomeusuario());
				usuarioLogin.get().setId(email.get().getId());
				usuarioLogin.get().setFoto(email.get().getFoto());
				usuarioLogin.get().setGenero(email.get().getGenero());
				usuarioLogin.get().setEmail(email.get().getEmail());
				usuarioLogin.get().setEmpregador(email.get().getEmpregador());
				
				

				
				return usuarioLogin;

			}
		}

		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	
	//Implementações
	

	public Usuario curtir(Long id) {

		Usuario usuario = buscarPostagemPeloId(id);
		usuario.setCurtidas(usuario.getCurtidas() + 1);
		return usuarioRepository.save(usuario);

	}

	public Usuario descurtir(Long id) {

		Usuario usuario = buscarPostagemPeloId(id);
		if (usuario.getCurtidas() > 0) {
			usuario.setCurtidas(usuario.getCurtidas() - 1);
		} 
		else {
			usuario.setCurtidas(0);
		}
		return usuarioRepository.save(usuario);

	}

	private Usuario buscarPostagemPeloId(Long id) {
		Usuario usuarioSalva = usuarioRepository.findById(id).orElse(null);
		if (usuarioSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalva;
	}
	

}
