package br.edu.ifpb.pweb2.sessoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import br.edu.ifpb.pweb2.sessoes.model.Carrinho;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @GetMapping
    public ModelAndView getCarrinho(ModelAndView model) {
        model.setViewName("carrinho");
        return model;
    }

    @PostMapping
    public ModelAndView addCarrinho(String id, Integer numItens, HttpSession session, ModelAndView model) {

        Carrinho carrinho = ((Carrinho) session.getAttribute("carrinho"));

        if (carrinho == null) {
            carrinho = new Carrinho();
        }
        if (numItens == null) {
            carrinho.adicioneItem(id);
        } else {
            carrinho.setQtdeItens(id, numItens);
        }
        session.setAttribute("carrinho", carrinho);
        model.setViewName("redirect:carrinho");
        return model;
    }
}
