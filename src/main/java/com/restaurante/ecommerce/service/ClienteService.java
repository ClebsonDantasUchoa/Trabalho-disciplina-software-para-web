package com.restaurante.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Pessoa;
import com.restaurante.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void cadastrar(Cliente cliente) {
		clienteRepository.save(cliente);
	}
}
