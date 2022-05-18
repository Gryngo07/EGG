package edu.egg.Libreria.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.egg.Libreria.Entidades.Autor;
import edu.egg.Libreria.Errores.ErrorServicio;
import edu.egg.Libreria.Repositorio.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired // la variable la inicializa el servidor de aplicaciones//
	private AutorRepositorio autorRepositorio;

	public void validarDatos(String nombreAutor, Boolean altaAutor) throws ErrorServicio {

		if (nombreAutor == null || nombreAutor.isEmpty()) {
			throw new ErrorServicio("El campo nombreAutor no puede estar vacío");
		}

		if (altaAutor == false) {
			throw new ErrorServicio("El campo altaAutor no puede estar vacío");
		}

		
		
	}

	@Transactional
	public Autor guarmodAutor(String idAutor, String nombreAutor, Boolean altaAutor) throws ErrorServicio {

		validarDatos(nombreAutor, altaAutor);

		Autor autor = null;

		if (idAutor == null || idAutor.isEmpty()) {
			autor = new Autor();
		} else {
			autor = autorRepositorio.findById(idAutor).orElse(null);
		}
		if (autor == null) {
			throw new ErrorServicio("No se pudo instanciar el nuevo autor");
		}

		autor.setNombre(nombreAutor);
		autor.setAlta(true);

		return autorRepositorio.save(autor);

	}

	@Transactional
	public void dardeBaja(String idAutor) throws ErrorServicio {

		Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(false);

			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("El autor no se encuentra en nuestra base de datos");
		}

	}

	@Transactional
	public void dardeAlta(String id) throws ErrorServicio {

		Optional<Autor> respuesta = autorRepositorio.findById(id);
		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(true);
		} else {
			throw new ErrorServicio("El autor no se encuentra en nuestra base de datos");
		}
	}
	public Autor buscarporNombre(String nombreAutor) throws ErrorServicio {
		if (nombreAutor == null || nombreAutor.isEmpty()) {
			throw new ErrorServicio("El nombreAutor del autor no pertenece a ninguno de nuestra base de datos");
		} else {
			return autorRepositorio.buscarPorNombre(nombreAutor);
		}

	}
//	@Transactional
//	public Autor modificarAutor(String id, String nombreAutor, Boolean altaAutor) throws ErrorServicio {
//
//		validarDatos(nombreAutor, altaAutor);
//		Optional<Autor> respuesta = autorRepositorio.findById(Id);
//
//		if (respuesta.isPresent()) {
//			Autor autor = respuesta.get();
//			autor.setNombre(nombreAutor);
//			autor.setAlta(true);
//
//			return autorRepositorio.save(autor);
//		} else {
//			throw new ErrorServicio("El autor no se encuentra en la base de datos");
//		}
//
//	}


}
