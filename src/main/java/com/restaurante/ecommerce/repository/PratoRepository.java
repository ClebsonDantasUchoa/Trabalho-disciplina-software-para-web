package com.restaurante.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.ecommerce.models.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>{

}
