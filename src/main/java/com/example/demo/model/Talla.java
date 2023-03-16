package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "talla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Talla.findAll", query = "SELECT t FROM Talla t"),
    @NamedQuery(name = "Talla.findByIdTalla", query = "SELECT t FROM Talla t WHERE t.idTalla = :idTalla"),
    @NamedQuery(name = "Talla.findByNombre", query = "SELECT t FROM Talla t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Talla.findByNumero", query = "SELECT t FROM Talla t WHERE t.numero = :numero")})

public class Talla implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false) @Column(name = "id_talla")
    private Integer idTalla;
    
    @Size(max = 25) @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull @Column(name = "numero")
    private short numero;
    
    @ManyToMany(mappedBy = "tallaCollection")
    private Collection<Producto> productoCollection;

    
    public Talla() {}

    public Talla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public Talla(Integer idTalla, short numero) {
        this.idTalla = idTalla;
        this.numero = numero;
    }
    

    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getNumero() {
        return numero;
    }

    public void setNumero(short numero) {
        this.numero = numero;
    }

    public Collection<Producto> productoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTalla != null ? idTalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Talla))
            return false;
        Talla other = (Talla) object;
        if ((this.idTalla == null && other.idTalla != null) || (this.idTalla != null && !this.idTalla.equals(other.idTalla)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Talla[ idTalla=" + idTalla + " ]";
    }    
}