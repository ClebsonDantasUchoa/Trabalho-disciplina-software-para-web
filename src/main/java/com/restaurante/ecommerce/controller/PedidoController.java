package com.restaurante.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		Cliente cliente = clienteService.recuperarUsuarioLogado(); 
		Prato prato = pratoService.buscarPrato(id);
		clienteService.adicionarPratoNoPedido(cliente, prato);
		System.out.println("-------------ADICIONAR--------------");
		System.out.println("Número de pedidos: " + cliente.getPedidos().size());
		System.out.println("\n");
		clienteRepository.save(cliente);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listarPedidos() {
		ModelAndView mv = new ModelAndView("Pedidos");
		Cliente cliente = clienteService.recuperarUsuarioLogado();
		System.out.println("-------------LISTAR--------------");
		System.out.println("Número de pedidos: " + cliente.getPedidos().size());
		System.out.println("Quantidade de pratos do ultimo pedido: " + cliente.ultimoPedido().getPratos().size());
		System.out.println("\n");
		List<Pedido> pedidos = cliente.getPedidos();
		Pedido pedidoAtual = pedidos.remove(pedidos.size()-1);
		mv.addObject("pedidoAtual", pedidoAtual);
		mv.addObject("pedidos", pedidos);
		
		return mv;
	}
	
	//
	@RequestMapping("/fechar")
	//public ModelAndView fecharPedido() {
	public ModelAndView fecharPedido(@RequestParam("endereco") String endereco) {
		System.out.println("-----------------------------");
		System.out.println("ENDEREÇO: " + endereco);
		Cliente cliente = clienteService.recuperarUsuarioLogado();
		
		cliente.finalizarUltimoPedido(endereco);
		
		clienteRepository.save(cliente);
		
		cliente = clienteService.recuperarUsuarioLogado();
		System.out.println("-------------FECHAR--------------");
		System.out.println("Número de pedidos: " + cliente.getPedidos().size());
		System.out.println("Quantidade de pratos do ultimo pedido: " + cliente.ultimoPedido().getPratos().size());
		System.out.println("\n");
		/*
		ModelAndView mv = new ModelAndView("Pedidos");
		List<Pedido> pedidos = cliente.getPedidos();
		Pedido pedidoAtual = pedidos.remove(pedidos.size()-1);
		mv.addObject("pedidoAtual", pedidoAtual);
		mv.addObject("pedidos", pedidos);
		*/
		ModelAndView mv = new ModelAndView("redirect:/pedido/listar");
		return mv;
	}
	
	@RequestMapping("/removerPrato/{id}")
	public ModelAndView removerPrato(@PathVariable Long id) {
		Cliente cliente = clienteService.recuperarUsuarioLogado();
		cliente.removerPratoDoPedidoAtual(id);
		clienteRepository.save(cliente);
		ModelAndView mv = new ModelAndView("redirect:../listar");
		return mv;
	}
		
}
