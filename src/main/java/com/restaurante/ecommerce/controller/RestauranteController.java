package com.restaurante.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Prato;
import com.restaurante.ecommerce.service.ClienteService;
import com.restaurante.ecommerce.service.PratoService;

@Controller
public class RestauranteController {
	
	@Autowired
	private PratoService pratoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/")
	public ModelAndView inicio() {
		List<Prato> pratos = pratoService.retornarTodosOsPratos(); 
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		Cliente cliente = (Cliente) clienteService.buscarPorId((long) 44);
		System.out.println("tamanho da lista de pedidos: " + cliente.getPedidos().size());
		return mv;	
	}
	
	@RequestMapping("/inicio")
	public ModelAndView index() {
		List<Prato> pratos = pratoService.retornarTodosOsPratos(); 
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		Cliente cliente = (Cliente) clienteService.buscarPorId((long) 44);
		System.out.println("tamanho da lista de pedidos: " + cliente.getPedidos().size());
		return mv;	
	}
	
	/*
	@RequestMapping("/login")
	public ModelAndView login() {
		List<Prato> pratos = pratoService.retornarTodosOsPratos(); 
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		Cliente cliente = (Cliente) clienteService.buscarPorId((long) 44);
		System.out.println("tamanho da lista de pedidos: " + cliente.getPedidos().size());
		return mv;	
	}
	*/

}
