package com.restaurante.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Pessoa;
import com.restaurante.ecommerce.service.ClienteService;

@Controller
public class PessoaController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cadastrarCliente")
	public ModelAndView cadastrar(Cliente cliente, Model model) {
		clienteService.cadastrar(cliente);
		ModelAndView mv = new ModelAndView("paginaInicialCliente");
		return mv;
	}
	
	@RequestMapping("/loginOuCadastro")
	public ModelAndView loginOuCadastro() {
		ModelAndView mv = new ModelAndView("loginOuCadastro");
		return mv;
	}

}
