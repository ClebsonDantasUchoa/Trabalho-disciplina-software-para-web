package com.restaurante.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Pedido;
import com.restaurante.ecommerce.models.Prato;
import com.restaurante.ecommerce.repository.ClienteRepository;
import com.restaurante.ecommerce.service.ClienteService;
import com.restaurante.ecommerce.service.PratoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/adicionarPrato/{id}")
	public ModelAndView adicionarPratoAoPedido(@PathVariable Long id) {
		Cliente cliente = (Cliente) clienteService.buscarPorId((long) 44);
		Prato prato = pratoService.buscarPrato(id);
		clienteService.adicionarPratoNoPedido(cliente, prato);
		clienteRepository.save(cliente);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listarPedidos() {
		ModelAndView mv = new ModelAndView("Pedidos");
		Cliente cliente = (Cliente) clienteService.buscarPorId((long) 44);
		
		List<Pedido> pedidos = cliente.getPedidos();
		Pedido pedidoAtual = pedidos.remove(pedidos.size()-1);
		mv.addObject("pedidoAtual", pedidoAtual);
		mv.addObject("pedidos", pedidos);
		
		return mv;
	}
	
}
