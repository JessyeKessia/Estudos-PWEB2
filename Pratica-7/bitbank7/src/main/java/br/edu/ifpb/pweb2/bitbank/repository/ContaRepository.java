package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Conta findByCorrentista(Correntista correntista);

    @Query("From Conta c left join fetch c.transacoes where c.numero = :numero")
    Conta findByNumeroWithTransacoes(String numero);

    @Query("select distinct c from Conta c left join fetch c.transacoes t where c.id = :id")
    Conta findByIdWithTransacoes(Integer id);
}