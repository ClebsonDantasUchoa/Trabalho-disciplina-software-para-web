package com.restaurante.ecommerce.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class ProjectFileUtils {
	
	public void salvarImagem(String caminho, MultipartFile imagem) {
		File file = new File(caminho);
		try {
			FileUtils.writeByteArrayToFile(file, imagem.getBytes());
		} catch (IOException e) {
			System.out.println("ERRO AO SALVAR AQUIVO: " + e.toString());
		}
		
	}
	
	public void apagarImagem(String caminho) {
		System.out.println("caminho da imagem: "+caminho);
		File file = new File(caminho);
		file.delete();
	}

}
