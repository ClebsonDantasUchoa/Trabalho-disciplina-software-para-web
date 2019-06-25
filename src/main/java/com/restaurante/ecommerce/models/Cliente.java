package com.restaurante.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Cliente extends Pessoa{
	
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy( value = "id" )
	private List<Pedido> pedidos;
	
	public Cliente() {
		pedidos = new ArrayList<Pedido>();
		inicilaizarPedido();
	}

	private void inicilaizarPedido() {		
		pedidos.add(pedidos.size(), new Pedido());
	}
	
	public void adicionarPratoAoPedido(Prato prato) {
		ultimoPedido().adicionarPrato(prato);
	}
	
	public void finalizarUltimoPedido(String endereco) {
		ultimoPedido().setConcluido();
		ultimoPedido().setEndereco(endereco);
		inicilaizarPedido();
	}
	
	public Pedido ultimoPedido() {
		return pedidos.get(pedidos.size()-1);
	}
	
	public boolean verificarPrato(Prato prato) {
		return ultimoPedido().getPratos().contains(prato);
	}
	
	public List<Pedido> getPedidos(){
		return this.pedidos;
	}
	
	public void removerPratoDoPedidoAtual(Long id) {
		ultimoPedido().removerPrato(id);
	}
	
}
