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
	
	@Autowired
	private PratoRepository pratoRepository;
	
	public void cadastrar(Prato prato, MultipartFile imagem) {
		pratoRepository.save(prato);
		String caminho = "imagens/pratos/"+prato.getId()+".jpg";
		ProjectFileUtils.salvarImagem(caminho, imagem);
	}
	
	public List<Prato> retornarTodosOsPratos() {
		return pratoRepository.findAll();
	}
	
}
