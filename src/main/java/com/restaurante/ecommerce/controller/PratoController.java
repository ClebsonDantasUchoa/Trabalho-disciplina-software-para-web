package com.restaurante.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		return mv;
	}
	
}
