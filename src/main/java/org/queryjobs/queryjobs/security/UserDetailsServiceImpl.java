package org.queryjobs.queryjobs.security;

import java.util.Optional;

import org.queryjobs.queryjobs.model.Usuario;
import org.queryjobs.queryjobs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> email = userRepository.findByEmail(userName);
		email.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return email.map(UserDetailsImpl::new).get();
	}
}



