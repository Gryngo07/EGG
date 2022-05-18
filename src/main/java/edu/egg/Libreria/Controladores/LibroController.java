package edu.egg.Libreria.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.egg.Libreria.Entidades.Autor;
import edu.egg.Libreria.Entidades.Editorial;
import edu.egg.Libreria.Repositorio.AutorRepositorio;
import edu.egg.Libreria.Repositorio.EditorialRepositorio;
import edu.egg.Libreria.Servicios.AutorServicio;
import edu.egg.Libreria.Servicios.EditorialServicio;
import edu.egg.Libreria.Servicios.LibroServicio;

@Controller
@RequestMapping("/")
public class LibroController {
	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private EditorialRepositorio editorialRepositorio;
	@Autowired
	private LibroServicio libroServicio;
	@Autowired
	private AutorServicio autorServicio;
	@Autowired
	private EditorialServicio editorialServicio;

	@GetMapping("/libro")
	public String index(ModelMap modelo) {
		List<Editorial>listaEditorial = editorialRepositorio.findAll();
		modelo.addAttribute("editoriales",listaEditorial);
		List<Autor>listaAutor=autorRepositorio.findAll();
		modelo.addAttribute("autores",listaAutor);
//		modelo.put("editoriales", editorialRepositorio.findAll());
//		modelo.put("autores", autorRepositorio.findAll());
		return "libro.html";
	}

	@PostMapping("/libro") // tiene que coincidir con la ruta que está en el form del html//
	public String guardarLibro(ModelMap modelo, 
			@RequestParam Autor autor, 
			@RequestParam String id,
			@RequestParam Long isbn, 
			@RequestParam String titulo, 
			@RequestParam Integer anio,
			@RequestParam Integer ejemplares,
			@RequestParam Boolean alta,
			@RequestParam MultipartFile archivo,
			@RequestParam Editorial editorial) throws Exception {
		try {

			libroServicio.guarmodLibro(archivo, id, isbn, titulo, anio, ejemplares, alta, autor, editorial);
			System.out.println("Libro " + titulo);
			modelo.put("exito", "Libro ingresado con éxito!!");
		} catch (Exception e) {
			e.getMessage();
			modelo.put("error", "No se ha podido guardar el libro");
		}
		return "libro.html";
	}

}
