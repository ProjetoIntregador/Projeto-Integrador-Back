package org.queryjobs.queryjobs.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min=1, max=500)
	private String tema;
	
	@NotNull
	@Size(min=1, max=2000)
	private String especificacao;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	@Size(min=1, max=2000)
	private String experiencia;
	
	private String imagem;

	@ManyToOne
	@JsonIgnoreProperties ("produto")
	private Usuario usuario;
	
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
}
	
