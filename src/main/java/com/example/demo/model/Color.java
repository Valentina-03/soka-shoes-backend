package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "color")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c"),
    @NamedQuery(name = "Color.findByIdColor", query = "SELECT c FROM Color c WHERE c.idColor = :idColor"),
    @NamedQuery(name = "Color.findByNombre", query = "SELECT c FROM Color c WHERE c.nombre = :nombre")})

public class Color implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull @Size(min = 1, max = 50)
    @Column(name = "id_color")
    private String idColor;
    
    @Size(max = 25) @Column(name = "nombre")
    private String nombre;
    
    @JoinTable(name = "producto_color", joinColumns = {
        @JoinColumn(name = "id_color", referencedColumnName = "id_color")}, inverseJoinColumns = {
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")})
    @ManyToMany
    private Collection<Producto> productoCollection;

    
    public Color() {}

    public Color(String idColor) {
        this.idColor = idColor;
    }
    

    public String getIdColor() {
        return idColor;
    }

    public void setIdColor(String idColor) {
        this.idColor = idColor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idColor != null ? idColor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Color))
            return false;
        Color other = (Color) object;
        if ((this.idColor == null && other.idColor != null) || (this.idColor != null && !this.idColor.equals(other.idColor)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Color[ idColor=" + idColor + " ]";
    }    
}