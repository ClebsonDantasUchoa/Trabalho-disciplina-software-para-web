package com.restaurante.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.restaurante.ecommerce.models.Prato;
import com.restaurante.ecommerce.service.PratoService;

@Controller
public class PratoController {
	
	@Autowired
	private PratoService pratoService;

	@PostMapping("/cadastrarPrato")
	public ModelAndView cadastrarPrato(Prato prato, @RequestParam(value="imagem") MultipartFile imagem) {
		pratoService.cadastrar(prato, imagem);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/cadastroPrato")
	public ModelAndView cadastroPrato() {
		ModelAndView mv = new ModelAndView("cadastroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@RequestMapping("/excluirPrato/{id}")
	public ModelAndView excluirPrato(@PathVariable Long id) {
		pratoService.excluirPrato(id);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/atualizarPrato/{id}")
	public ModelAndView atualizarPrato(@PathVariable Long id) {
		Prato prato = pratoService.buscarPrato(id);
		ModelAndView mv = new ModelAndView("cadastroPrato");
		mv.addObject("prato", prato);
		return mv;
	}
	
}
