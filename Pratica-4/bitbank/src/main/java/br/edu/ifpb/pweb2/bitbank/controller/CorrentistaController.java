package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;



@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {
    
    // injeção de dependência para o repositório
    @Autowired
    private CorrentistaService correntistasService;

    @GetMapping("/form")
    public String getForm(Correntista correntista, Model model) {
        model.addAttribute("correntista", correntista);
        return "correntistas/form";
    }
    
    @PostMapping
    public String save(Correntista correntista, Model model) {
        correntistasService.save(correntista);

        model.addAttribute("mensagem", "Correntista cadastrado com sucesso!");
        model.addAttribute("correntistas", correntistasService.findAll());
        return "correntistas/list";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("correntistas", correntistasService.findAll());
        return "correntistas/list";
    }

    @GetMapping("/{id}")
    public String getCorrentistaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("correntista", correntistasService.findById(id));
        return "correntistas/form";
    }
}
