package br.edu.ifpb.pweb2.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.validation.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
