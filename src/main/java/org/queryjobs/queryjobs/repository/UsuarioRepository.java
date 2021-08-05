package org.queryjobs.queryjobs.repository;

import java.util.List;
import java.util.Optional;

import org.queryjobs.queryjobs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		
		public List<Usuario> findAllByEmailContainingIgnoreCase(String email);
		public Optional<Usuario> findByEmail(String email);
		


}
