package org.queryjobs.queryjobs.repository;

import java.util.List;
import org.queryjobs.queryjobs.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

	public List <Produto> findAllByEspecificacaoContainingIgnoreCase (String especificacao);
}
