package org.queryjobs.queryjobs.repository;

import java.math.BigDecimal;
import java.util.List;
import org.queryjobs.queryjobs.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

	public List <Produto> findAllByEspecificacaoContainingIgnoreCase (String especificacao);
	public List <Produto> findAllByExperienciaContainingIgnoreCase (String experiencia);
	public List <Produto> findAllByTemaContainingIgnoreCase (String tema);
	public List <Produto> findAllByValorGreaterThan (BigDecimal valor);
	public List <Produto> findAllByValorLessThan (BigDecimal valor);
}
