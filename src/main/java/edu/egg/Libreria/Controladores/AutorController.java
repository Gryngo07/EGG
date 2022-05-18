package edu.egg.Libreria.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.egg.Libreria.Servicios.AutorServicio;

@Controller
@RequestMapping("")
public class AutorController {
	
	@Autowired
	private AutorServicio autorServicio;
	
	@GetMapping("/autor")
	public String autor() {
		return "autor.html";
	}
	
	@PostMapping("/autor")//tiene que coincidir con la ruta que está en el form del html//
    public String guardarAutor(ModelMap modelo,@RequestParam String idAutor, @RequestParam String nombreAutor,@RequestParam Boolean altaAutor) throws Exception {
        try {
            autorServicio.guarmodAutor(idAutor, nombreAutor, altaAutor);
            System.out.println("Autor " + nombreAutor);
            modelo.put("exito", "Autor ingresado con éxito!!");
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", "No se ha podido guardar el autor");
        }
        return "autor.html";
    }


}
