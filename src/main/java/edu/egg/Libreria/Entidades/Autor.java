package edu.egg.Libreria.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

	@Id
	@GeneratedValue(generator = "uuid") // Genera el id automatico//
	@GenericGenerator(name = "uuid", strategy = "uuid2") // Convencion para evitar duplicados//
	private String idAutor;
	@Column(nullable = false)
	private String nombreAutor;
	private Boolean altaAutor;

	public Autor() {
	}

	public Autor(String nombreAutor, Boolean altaAutor) {

		this.nombreAutor = nombreAutor;
		this.altaAutor = altaAutor;
	}

	public String getidAutor() {
		return idAutor;
	}

	public void setidAutor(String idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombre() {
		return nombreAutor;
	}

	public void setNombre(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public Boolean getAlta() {
		return altaAutor;
	}

	public void setAlta(Boolean altaAutor) {
		this.altaAutor = altaAutor;
	}

}
