package com.restaurante.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.ecommerce.models.Cliente;
import com.restaurante.ecommerce.models.Pessoa;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByEmail(String email);
}
