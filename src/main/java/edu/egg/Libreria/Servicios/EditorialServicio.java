package edu.egg.Libreria.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.egg.Libreria.Entidades.Editorial;
import edu.egg.Libreria.Errores.ErrorServicio;
import edu.egg.Libreria.Repositorio.EditorialRepositorio;

@Service
public class EditorialServicio {

	@Autowired
	private EditorialRepositorio editorialRepositorio;

	public void validarDatos(String nombreEditorial, Boolean altaEditorial) throws ErrorServicio {

		if (nombreEditorial == null || nombreEditorial.isEmpty()) {
			throw new ErrorServicio("El campo nombreEditorial no puede estar vacío");
		}

		if (altaEditorial == false) {
			throw new ErrorServicio("El editorial no puede estar dado de baja");
		}

	}

	@Transactional
	public Editorial guarmodEditorial(String idEditorial, String nombreEditorial, Boolean altaEditorial) throws ErrorServicio {


		validarDatos(nombreEditorial, altaEditorial);
		
		Editorial editorial = null;
		if (idEditorial == null || idEditorial.isEmpty()) {
			editorial = new Editorial();
		} else {
			editorial = editorialRepositorio.findById(idEditorial).orElse(null);
		}
		if (editorial == null) {
			throw new ErrorServicio("No se pudo instanciar el nuevo editorial");
		}

		editorial.setNombre(nombreEditorial);
		editorial.setAlta(true);

		return editorialRepositorio.save(editorial);

	}
	@Transactional
	public void dardeBaja(String Id) throws ErrorServicio {

		Optional<Editorial> respuesta = editorialRepositorio.findById(Id);
		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get();
			editorial.setAlta(false);

			editorialRepositorio.save(editorial);
		} else {
			throw new ErrorServicio("El editorial no se encuentra en nuestra base de datos");
		}

	}
	@Transactional
	public void dardeAlta(String Id) throws ErrorServicio {
		Optional<Editorial> respuesta = editorialRepositorio.findById(Id);
		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get();
			editorial.setAlta(true);
		} else {
			throw new ErrorServicio("El editorial no se encuentra en nuestra base de datos");
		}
	}
	public Editorial buscarporNombre(String nombreEditorial) throws ErrorServicio {
		if (nombreEditorial == null || nombreEditorial.isEmpty()) {
			throw new ErrorServicio("El nombre del editorial no pertenece a ninguno de nuestra base de datos");
		} else {
			return editorialRepositorio.buscarPorNombre(nombreEditorial);
		}

	}

//	@Transactional
//	public Editorial modificarEditorial(String id, String nombreEditorial, Boolean altaEditorial) throws ErrorServicio {
//		validarDatos(nombreEditorial, altaEditorial);
//		Optional<Editorial> respuesta = editorialRepositorio.findById(idEditorial);
//		if (respuesta.isPresent()) {
//			Editorial editorial = respuesta.get();// que hace esto exactamente??//
//			editorial.setNombre(nombreEditorial);
//			editorial.setAlta(true);
//			return editorialRepositorio.save(editorial);
//		} else {
//			throw new ErrorServicio("La editorial indicada no se encuentra en nuestra base de datos");
//		}
//
//	}//se da de baja este metodo para utilizar solamente el guardarEditorial para realizar las operaciones crear y modificar. 

}
