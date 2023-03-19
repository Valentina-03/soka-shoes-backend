package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT d FROM Ciudad d"),
    @NamedQuery(name = "Ciudad.findByidCiudad", query = "SELECT d FROM Ciudad d WHERE d.idCiudad = :idCiudad"),
    @NamedQuery(name = "Ciudad.findByNombre", query = "SELECT d FROM Ciudad d WHERE d.nombre = :nombre")})

public class Ciudad implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer idCiudad;

    @Column(name = "nombre")
    private String nombre;
    
    @JoinColumn(name = "departamento", referencedColumnName = "id_departamento")
    @ManyToOne
    private Departamento departamento;

    
    public Ciudad() {}

    public Ciudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    public Integer getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ciudad))
            return false;
        Ciudad other = (Ciudad) object;
        if ((this.idCiudad == null && other.idCiudad != null) || (this.idCiudad != null && !this.idCiudad.equals(other.idCiudad)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Categoria[ idCiudad=" + idCiudad + " ]";
    }
    
}