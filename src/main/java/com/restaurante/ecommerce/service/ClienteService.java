package com.restaurante.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Pessoa;
import com.restaurante.ecommerce.models.Prato;
import com.restaurante.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void cadastrar(Cliente cliente) {
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		clienteRepository.save(cliente);
	}
	
	public Pessoa buscarPorId(Long id) {
		return clienteRepository.getOne(id);
	}
	
	public Cliente buscarPorEmail(String email) {
		return clienteRepository.findByEmail(email);
	}
	
	public void adicionarPratoNoPedido(Cliente cliente, Prato prato) {
		if(!cliente.verificarPrato(prato)) {
			cliente.adicionarPratoAoPedido(prato);
		}		
	}
	
	public Cliente recuperarUsuarioLogado() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		return (Cliente) buscarPorEmail(user.getUsername());
	}
	
}
