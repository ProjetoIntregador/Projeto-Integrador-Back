package org.queryjobs.queryjobs.repository;

import java.util.List;

import org.queryjobs.queryjobs.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List <Categoria> findAllByTipotrabalhoContainingIgnoreCase (String tipotrabalho);
	public List <Categoria> findAllByPalavrachaveContainingIgnoreCase (String palavrachave);
}
