package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import org.springframework.ui.Model;
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
    
    @GetMapping("/{id}")
    public String getContaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("conta", contaService.findById(id));
        return "contas/form";
    }

}
