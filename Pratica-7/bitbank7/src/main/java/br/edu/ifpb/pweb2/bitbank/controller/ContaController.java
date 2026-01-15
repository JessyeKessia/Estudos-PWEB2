package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.model.Transacao;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import br.edu.ifpb.pweb2.bitbank.model.Conta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// controla as requisições relacionadas às contas
@Controller
// mapeia as requisições para /contas
@RequestMapping("/contas")
public class ContaController {

    // injeção de dependência para o repositório
    @Autowired
    private ContaService contaService;

    @Autowired
    private CorrentistaService correntistaService;

    @ModelAttribute("correntistaItems")
    public List<Correntista> getCorrentistas() {
        return correntistaService.findAll();
    }

    @GetMapping("/nuconta")
    public String getNuConta() {
        return "contas/operacao";
    }

    @PostMapping("/operacao")
    public ModelAndView operacaoConta(String nuConta, Transacao transacao, ModelAndView modelAndView) {
        if (nuConta != null && transacao.getValor() == null) {
            // encontra a conta pelo número da conta com as transações
            Conta contaEncontrada = contaService.findByNumeroWithTransacoes(nuConta);
            // se a conta encontrada é diferente de null
            if (contaEncontrada != null) {
                // manda os dados da conta encontrada para a view
                modelAndView.addObject("conta", contaEncontrada);
                // cria uma nova transação para a conta
                modelAndView.addObject("transacao", new Transacao());
                // envia para a página de operação
                modelAndView.setViewName("contas/operacao");
            } else {
                // caso a conta seja null, mostra a mensagem de erro
                modelAndView.addObject("mensagem", "Conta inexistente!");
                // envia para a página de operação
                modelAndView.setViewName("contas/operacao");
            }
        } else {
            // pega a conta encontrada pelo número
            Conta contaEncontrada = contaService.findByNumeroWithTransacoes(nuConta);
            // adiciona uma nova transação a conta encontrada
            contaEncontrada.addTransacao(transacao);
            // salva a conta com a nova transação
            contaService.save(contaEncontrada);
            // redireciona para a página de operação da conta atualizada
            return addTransacaoConta(contaEncontrada.getId(), modelAndView);
        }
        return modelAndView;
    }

    @GetMapping("/{id}/transacoes")
    public ModelAndView addTransacaoConta(@PathVariable("id") Integer id, ModelAndView modelAndView) {
        Conta conta = contaService.findByIdWithTransacoes(id);
        modelAndView.addObject("conta", conta);
        modelAndView.setViewName("contas/transacoes");
        return modelAndView;
    }
    @PostMapping
    public ModelAndView adicioneConta(Conta conta, ModelAndView modelAndView) {
        if (conta.getCorrentista() != null && conta.getCorrentista().getId() != null) {
            Correntista correntista = correntistaService.findById(conta.getCorrentista().getId());
            conta.setCorrentista(correntista);
    }

    contaService.save(conta);
    modelAndView.setViewName("contas/list");
    modelAndView.addObject("contas", contaService.findAll());
    return modelAndView;
    }

// mapeia as requisições para /form
    @GetMapping("/form")
    public ModelAndView getForm(ModelAndView modelAndView) {

        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta", new Conta(new Correntista()));
        return modelAndView;
    }

    @GetMapping
    public ModelAndView liste(ModelAndView modelAndView){
        modelAndView.setViewName("contas/list");
        modelAndView.addObject("contas", contaService.findAll());
        return modelAndView;
    }
}
