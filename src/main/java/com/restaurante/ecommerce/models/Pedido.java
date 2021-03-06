package com.restaurante.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Prato> pratos;
	
	private boolean concluido;
	
	private String endereco;
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Pedido() {
		pratos = new ArrayList<Prato>();
		this.concluido = false;
	}
	
	public void removerPrato(Long id) {
		for(Prato prato: pratos)
			if(prato.getId() == id) {
				pratos.remove(prato);
				break;
			}
	}
	
	public float calcularValor() {
		float valor = 0f;
		for(Prato prato: pratos) {
			valor += prato.getValor();
		}
		return valor;
	}
	
	public void setConcluido() {
		this.concluido = true;
	}
	
	public void adicionarPrato(Prato prato) {
		this.pratos.add(prato);
	}
	
	public List<Prato> getPratos(){
		return this.pratos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

}
