package edu.egg.Libreria.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.egg.Libreria.Entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

	@Query("SELECT e FROM Editorial e WHERE e.nombreEditorial LIKE %:nombreEditorial%")
	public Editorial buscarPorNombre(@Param("nombreEditorial") String nombreEditorial);

}
