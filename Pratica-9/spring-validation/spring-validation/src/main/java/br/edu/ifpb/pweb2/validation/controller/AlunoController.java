package br.edu.ifpb.pweb2.validation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.validation.model.Aluno;
import br.edu.ifpb.pweb2.validation.repository.AlunoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository repo;

	@GetMapping("/form")
	public ModelAndView form(ModelAndView mav, Aluno aluno) {
		mav.setViewName("alunos/form");
		mav.addObject("aluno", new Aluno());
		return mav;
	}

	@GetMapping
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("alunos/list");
		mav.addObject("alunos", repo.findAll());
		return mav;
	}

	@PostMapping
	public ModelAndView save(@Valid Aluno aluno, BindingResult result, ModelAndView mav) {
		if (!result.hasErrors()) {
			repo.save(aluno);
			mav.setViewName("redirect:/alunos");
		} else {
			mav.addObject("mensagem", "Erros de validação!");
			mav.setViewName("alunos/form");
		}
		return mav;
	}

	@RequestMapping("/{id}/delete")
	public ModelAndView delete(@PathVariable Integer id, ModelAndView mav, RedirectAttributes attr) {
		Optional<Aluno> opAluno = repo.findById(id);
		if (opAluno.isPresent()) {
			repo.delete(opAluno.get());
		} else {
			attr.addAttribute("mensagem", "Aluno inexistente!");
		}
		mav.setViewName("redirect:/alunos");
		return mav;

	}

	@RequestMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, ModelAndView mav, RedirectAttributes attr) {
		Optional<Aluno> opAluno = repo.findById(id);
		if (!opAluno.isPresent()) {
			attr.addAttribute("mensagem", "Aluno inexistente!");
		}
		mav.setViewName("redirect:/alunos");
		return mav;

	}

}
