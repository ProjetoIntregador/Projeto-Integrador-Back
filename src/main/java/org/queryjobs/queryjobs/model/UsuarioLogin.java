package org.queryjobs.queryjobs.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UsuarioLogin {
	
	private long id;

	private String nomeusuario; //ok
	private String empregador; //ok
	private LocalDate datanascimento;
	private String email; // ok
	private String senha; // ok 
	private String genero; // ok
	private String foto; //ok
	private String token; // ok


}
