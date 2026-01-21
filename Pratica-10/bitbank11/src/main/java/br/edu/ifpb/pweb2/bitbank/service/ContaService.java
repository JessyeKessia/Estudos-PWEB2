package br.edu.ifpb.pweb2.bitbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;

@Component
public class ContaService implements Service<Conta, Integer> {
    
    @Autowired
    ContaRepository contaRepository;

    @Override
    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Conta findById(Integer id) {
        return contaRepository.findById(id).orElse(null);
    }

    @Override
    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta findByCorrentista(Correntista correntista) {
        return contaRepository.findByCorrentista(correntista);
    }

    public Conta findByNumeroWithTransacoes(String numero) {
        return contaRepository.findByNumeroWithTransacoes(numero);
    }

    public Conta findByIdWithTransacoes(Integer id) {
        return contaRepository.findByIdWithTransacoes(id);
    }

    public void deleteById(Integer id) {
        contaRepository.deleteById(id);
    }
}

