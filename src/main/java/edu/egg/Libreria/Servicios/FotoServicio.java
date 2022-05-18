package edu.egg.Libreria.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.egg.Libreria.Entidades.Foto;
import edu.egg.Libreria.Errores.ErrorServicio;
import edu.egg.Libreria.Repositorio.FotoRepositorio;

@Service
public class FotoServicio {

	@Autowired
	public FotoRepositorio fotoRepositorio;

	@Transactional
	public Foto guardarmodificarFoto(String id, MultipartFile archivo) throws ErrorServicio {
		Foto foto = null;
		if (archivo == null || archivo.isEmpty()) {

			foto = new Foto();
		} else {
			foto = fotoRepositorio.findById(id).orElse(null);

		}
		if (foto == null) {
			throw new ErrorServicio("No se ha podido cargar la foto indicada");
		}
		try {
			foto.setMime(archivo.getContentType());
			foto.setNombre(archivo.getName());
			foto.setContenido(archivo.getBytes());// puede disparar un error,porque?//

			return fotoRepositorio.save(foto);// si todo funciona bien se persiste la foto//
		} catch (Exception e) {
			System.err.println(e.getMessage());// si ocurre un error al cargar el contenido imprime un error
			// para analizarlo y devuelve una foto nula//
		}

		return null;
	}
}