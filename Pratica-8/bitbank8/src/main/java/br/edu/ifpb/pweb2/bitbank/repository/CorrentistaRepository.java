package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.List;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

    // 1. O método é chamado
    // 2. O Spring cria a consulta automaticamente
    // 3. O Hibernate executa o SQL no banco
    // 4. O resultado da tabela correntista é convertido em um objeto Correntista
    // 5. O objeto é retornado para a aplicação
    // 6. Se não encontrar nada: Retorna null
    Correntista findByEmail(String email);

}