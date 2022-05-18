package edu.egg.Libreria.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.egg.Libreria.Entidades.Autor;
import edu.egg.Libreria.Entidades.Editorial;
import edu.egg.Libreria.Entidades.Foto;
import edu.egg.Libreria.Entidades.Libro;
import edu.egg.Libreria.Errores.ErrorServicio;
import edu.egg.Libreria.Repositorio.LibroRepositorio;

@Service
public class LibroServicio {

	@Autowired // la variable la inicializa el servidor de aplicaciones//
	private LibroRepositorio libroRepositorio;
	@Autowired
	private FotoServicio fotoServicio;

	public void validarDatos(Long isbn, String titulo, Integer anio, Integer ejemplares, Autor autor,
			Editorial editorial) throws ErrorServicio {
		if (isbn == null) {
			throw new ErrorServicio("El campo isbn no puede estar vacío o no corresponde a un codigo existente");
		}
		if (titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("El campo título no puede estar vacío o no corresponde a un libro existente");
		}
		if (anio == null) {
			throw new ErrorServicio("El campo año no puede estar vacío");
		}
		if (ejemplares == null) {
			throw new ErrorServicio("El campo ejemplares no puede estar vacío");
		}
		// cómo valido la Editorial y el Autor??//
	}

	@Transactional
	public void guarmodLibro(MultipartFile archivo,String id, Long isbn, String titulo, Integer anio, Integer ejemplares,Boolean alta, Autor autor,
			Editorial editorial) throws ErrorServicio {//este método sirve para guardar y para modificar//

		validarDatos(isbn, titulo, anio, ejemplares, autor, editorial);
		Libro libro=null;
		
		if (id==null||id.isEmpty()) {
			libro = new Libro();
		}else {
		 libro=libroRepositorio.findById(id).orElse(null);
		}
		if (libro==null) {
			throw new ErrorServicio("No se pudo instanciar el nuevo libro");
		}
		libro.setIsbn(isbn);
		libro.setTitulo(titulo);
		libro.setAnio(anio);
		libro.setEjemplares(ejemplares);
		libro.setAlta(true);
		Foto foto = fotoServicio.guardarmodificarFoto(id, archivo);//el método tiene la validación para ver si ya existe una foto)
		libro.setFoto(foto);

		libroRepositorio.save(libro);// es el encargado de transformar el objeto en una o mas tablas de la base de
										// datos//

	}

	@Transactional
	public void deshabilitarLibro(String id,Boolean alta) throws ErrorServicio {
		
		Optional<Libro>respuesta= libroRepositorio.findById(id);
		
		if (respuesta.isPresent()) {
		Libro libro=respuesta.get();
		libro.setAlta(alta=false);
		libroRepositorio.save(libro);
		}else {
			throw new ErrorServicio("El libro indicado no se encuentra en nuestra base de datos");
		}
	}
	@Transactional
	public void habilitarLibro(String id,Boolean alta) throws ErrorServicio {
		
		Optional<Libro>respuesta= libroRepositorio.findById(id);
		
		if (respuesta.isPresent()) {
		Libro libro=respuesta.get();
		libro.setAlta(alta=true);
		libroRepositorio.save(libro);
		}else {
			throw new ErrorServicio("El libro indicado no se encuentra en nuestra base de datos");
		}
	}

//	@Transactional
//	public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Autor autor,
//			Editorial editorial) throws ErrorServicio {
//		
//		validarDatos(isbn, titulo, anio, ejemplares, autor, editorial);
//
//		Optional<Libro> respuesta = libroRepositorio.findByid(id);
//		
//		if (respuesta.isPresent()) {
//			Libro libro = respuesta.get();
//			libro.setIsbn(isbn);
//			libro.setTitulo(titulo);
//			libro.setAnio(anio);
//			libro.setEjemplares(ejemplares);
//		
//			
//			libroRepositorio.save(libro);
//		}else {
//			throw new ErrorServicio("El libro indicado no se encuentra en nuestra base de datos");
//		}
//	}

	public Libro buscarporIsbn(Long isbn) throws ErrorServicio {
		if (isbn == null) {
			throw new ErrorServicio("El campo isbn no puede estar vacío");
		}
		return libroRepositorio.buscarporIsbn(isbn);
		//comentario de prueba

	}

	public List<Libro> buscarPorTitulo(String titulo) throws ErrorServicio {
		if (titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("El campo título no puede estar vacío");
		}
		return libroRepositorio.buscarPorTitulo(titulo);
	}

	public List<Libro> buscarPorAutor(String nombreAutor) throws ErrorServicio {
		if (nombreAutor == null || nombreAutor.isEmpty()) {
			throw new ErrorServicio("El campo nombre no puede estar vacio");
		}
		return libroRepositorio.buscarPorAutor(nombreAutor);

	}

	public List<Libro> buscarPorEditorial(String nombreEditorial) throws ErrorServicio {
		if (nombreEditorial == null || nombreEditorial.isEmpty()) {
			throw new ErrorServicio("El campo nombre no puede estar vacio");
		}
		return libroRepositorio.buscarPorEditorial(nombreEditorial);
	}

	public void testMetodo(){
		System.out.println("Borrenme");
	}
	
}

//que pasa si quiero modificarle el autor a un libro o viseversa?//