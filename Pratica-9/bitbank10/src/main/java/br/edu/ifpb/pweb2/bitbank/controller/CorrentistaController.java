package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {
    
    // injeção de dependência para o repositório
    @Autowired
    private CorrentistaService correntistasService;

    @GetMapping("/form")
    public ModelAndView getForm(Correntista correntista, ModelAndView model) {
        model.setViewName("correntistas/form");
        model.addObject("correntista", correntista);
        return model;
    }
    
    @PostMapping
    public ModelAndView save(@Valid Correntista correntista, BindingResult result, ModelAndView model, RedirectAttributes atributo) {
        if (!result.hasErrors()) {
            correntistasService.save(correntista);
            atributo.addFlashAttribute("mensagem", "Correntista cadastrado com sucesso!");
            model.setViewName("redirect:/correntistas");
            return model;
        } else {
            model.setViewName("correntistas/form");
            return model;
        }
    }

    @GetMapping
    public ModelAndView list(ModelAndView model) {
        model.addObject("correntistas", correntistasService.findAll());
        model.setViewName("correntistas/list");
        return model;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getCorrentistaById(@PathVariable("id") Integer id, ModelAndView model) {
        model.addObject("correntista", correntistasService.findById(id));
        model.setViewName("correntistas/form");
        return model;
    }
}
