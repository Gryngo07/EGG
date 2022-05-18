package edu.egg.Libreria.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idEditorial;
    @Column (unique = true, nullable = false)
    private String nombreEditorial;
    private Boolean altaEditorial;

    public Editorial() {
    }

    public Editorial( String nombreEditorial, Boolean altaEditorial) {
        
        this.nombreEditorial = nombreEditorial;
        this.altaEditorial = altaEditorial;
    }

    public String getidEditorial() {
        return idEditorial;
    }

    public void setidEditorial(String idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombreEditorial;
    }

    public void setNombre(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public Boolean getAlta() {
        return altaEditorial;
    }

    public void setAlta(Boolean altaEditorial) {
        this.altaEditorial = altaEditorial;
    }

    @Override
    public String toString() {
        return "Editorial{" + "idEditorial=" + idEditorial + ", nombreEditorial=" + nombreEditorial + ", alta=" + altaEditorial + '}';
    }
    
    
}
   


