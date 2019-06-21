package com.restaurante.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.User;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.repository.ClienteRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) throw new UsernameNotFoundException("Usuário não encontrado");
		return new User(cliente.getUsername(),cliente.getPassword(),true,true,true,true,cliente.getAuthorities());
	}

}
