package br.edu.ifpb.pweb2.bitbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.model.User;
import br.edu.ifpb.pweb2.bitbank.repository.UserRepository;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {
    
    // injeção de dependência para o repositório
    @Autowired
    private CorrentistaService correntistasService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/form")
    public ModelAndView getForm(Correntista correntista, ModelAndView model) {
        model.setViewName("correntistas/form");
        model.addObject("correntista", correntista);
        return model;
    }
    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findByEnableTrue();
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
