package br.edu.ifpb.pweb2.bitbank.controller;

import com.seuprojeto.model.Correntista;

import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import main.java.br.edu.ifpb.pweb2.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CorrentistaService correntistaService;


    @GetMapping("/login")
    public ModelAndView getLoginForm(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("usuario", new Correntista());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView validarLogin(Correntista correntista, HttpSession session, ModelAndView model, RedirectAttributes redirectAttributes) {
    
        if ((correntista = this.isValido(correntista)) != null) {
            session.setAttribute("usuario", correntista);
            model.setViewName("redirect:/home");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
            model.setViewName("redirect:/auth");
        }
        return model;

    }

    private Correntista isValido(Correntista correntista) {
        // Acha o correntista pelo Email
        Correntista correntistaEncontrado = correntistaService.findByEmail(correntista.getEmail());
        
        boolean valido = false;
        if (correntistaEncontrado != null) {
            if (PasswordUtil.checkPassword(correntista.getSenha(), correntistaEncontrado.getSenha())) {
                valido = true;
            }
        }
        return valido ? correntistaEncontrado : null;
    }


    // Mudança para fazer aqui no logout
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, ModelAndView model) {
        session.invalidate();
        model.setViewName("redirect:/auth/login");
        return model;
    }

}
