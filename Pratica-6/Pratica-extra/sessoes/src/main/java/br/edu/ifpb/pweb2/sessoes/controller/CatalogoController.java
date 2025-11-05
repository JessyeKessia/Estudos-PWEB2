package br.edu.ifpb.pweb2.sessoes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifpb.pweb2.sessoes.model.Catalogo;

@Controller
@RequestMapping("/catalogo")
class CatalogoController {

    @GetMapping()
    public ModelAndView getMethodName(ModelAndView model) {

        model.addObject("catalogo", Catalogo.getItens());

        model.setViewName("catalogo");
        return model;

    }
    
}
