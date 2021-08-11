package org.queryjobs.queryjobs.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@Entity
@Table(name = "tb_Categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="O atributo tipotrabalho é obrigatório!")
	@Size(min=1, max=250, message="O atributo descrição deve conter no mínimo 1 carecter e no máximo 250")
	private String tipotrabalho;

	@NotNull(message="O atributo descrição é obrigatório!")
	@Size(min=1, max=250, message="O atributo descrição deve conter no mínimo 1 carecter e no máximo 250")
	private String descricao;	
	
	@NotNull(message="O atributo palavrachave é obrigatório!")
	@Size(min=1, max=250, message="O atributo descrição deve conter no mínimo 1 carecter e no máximo 250")
	private String palavrachave;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
}
