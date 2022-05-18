package edu.egg.Libreria.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.egg.Libreria.Servicios.EditorialServicio;

@Controller
@RequestMapping("/")
public class EditorialController {
	
	@Autowired
	private EditorialServicio editorialServicio;
	
	@GetMapping("/editorial")
	public String editorial() {
		return "editorial.html";
	}
	
	@PostMapping("/editorial")//tiene que coincidir con la ruta que está en el form del html//
    public String guardarEditorial(ModelMap modelo,@RequestParam String idEditorial, @RequestParam String nombreEditorial,@RequestParam Boolean altaEditorial) throws Exception {
        try {
            editorialServicio.guarmodEditorial(idEditorial, nombreEditorial, altaEditorial);
            System.out.println("Editorial " + nombreEditorial);
            modelo.put("exito", "Editorial ingresada con éxito!!");
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", "No se ha podido guardar el editorial");
        }
        return "editorial.html";
    }

}
