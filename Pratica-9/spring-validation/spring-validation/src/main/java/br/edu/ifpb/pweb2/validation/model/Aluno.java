package br.edu.ifpb.pweb2.validation.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import br.edu.ifpb.pweb2.validation.validator.CodCursoValid;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message="Nome é obrigatório")
	@Length(min=5, max=6, message="Nome não deve ser muito grande, corno")
	private String nome;

	@NotBlank(message="Matricula obrigatória")
	private String matricula;

	@Past(message="A data deve estar no passado")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="Data de nascimento é obrigatória")
	private LocalDate dataNascimento;

	private String email;

	@CodCursoValid
	private String codCurso;

}