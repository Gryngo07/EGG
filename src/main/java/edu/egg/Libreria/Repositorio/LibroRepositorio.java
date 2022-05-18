package edu.egg.Libreria.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.Libreria.Entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
	
	@Query ("Select c from Libro c WHERE c.isbn= :isbn")
	public Libro buscarporIsbn(@Param("isbn")Long isbn);

	@Query("SELECT c from Libro c WHERE c.titulo Like %:titulo%")
	public List<Libro> buscarPorTitulo(@Param("titulo") String titulo);

	@Query("SELECT c from Libro c WHERE c.autor.nombreAutor= :nombreAutor")
	public List<Libro> buscarPorAutor(@Param("nombreAutor") String nombreAutor);

	@Query("SELECT c from Libro c WHERE c.editorial.nombreEditorial= :nombreEditorial")
	public List<Libro> buscarPorEditorial(@Param("nombreEditorial") String nombreEditorial);

}
