package com.restaurante.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.restaurante.ecommerce.models.Prato;
import com.restaurante.ecommerce.repository.PratoRepository;
import com.restaurante.ecommerce.util.ProjectFileUtils;

@Service
public class PratoService {
	
	private ProjectFileUtils projectFileUtils = new ProjectFileUtils(); 
	
	@Autowired
	private PratoRepository pratoRepository;
	
	public void cadastrar(Prato prato, MultipartFile imagem) {
		pratoRepository.save(prato);
		String caminho = "imagens/pratos/"+prato.getId()+".jpg";
		if(!imagem.isEmpty()) projectFileUtils.salvarImagem(caminho, imagem);
	}
	
	public List<Prato> retornarTodosOsPratos() {
		return pratoRepository.findAll();
	}

	public void excluirPrato(Long id) {
		String caminho = "imagens/pratos/"+id+".jpg";
		System.out.println(caminho);
		projectFileUtils.apagarImagem(caminho);
		pratoRepository.deleteById(id);
	}
	
	public Prato buscarPrato(Long id) {
		return pratoRepository.getOne(id);
	}
	
}
