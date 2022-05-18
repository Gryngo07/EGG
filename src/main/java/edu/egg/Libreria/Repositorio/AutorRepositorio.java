package edu.egg.Libreria.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.Libreria.Entidades.Autor;



@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {

	@Query("SELECT a FROM Autor a WHERE a.nombreAutor LIKE %:nombreAutor%")
	public Autor buscarPorNombre(@Param("nombreAutor") String nombreAutor);

}
