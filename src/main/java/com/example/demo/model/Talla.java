package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "talla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Talla.findAll", query = "SELECT t FROM Talla t"),
    @NamedQuery(name = "Talla.findByIdTalla", query = "SELECT t FROM Talla t WHERE t.idTalla = :idTalla"),
    @NamedQuery(name = "Talla.findByNumero", query = "SELECT t FROM Talla t WHERE t.numero = :numero")})

public class Talla implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talla")
    private Integer idTalla;
    
    @Column(name = "numero")
    private Short numero;
    
    @OneToMany(mappedBy = "talla", orphanRemoval = true)
    @JsonIgnore
    private Collection<DetalleProducto> detalleProductoCollection;

    
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

    public short getNumero() {
        return numero;
    }

    public void setNumero(short numero) {
        this.numero = numero;
    }

    public Collection<DetalleProducto> getDetalleProductoCollection() {
        return detalleProductoCollection;
    }

    public void setProductoCollection(Collection<DetalleProducto> detalleProductoCollection) {
        this.detalleProductoCollection = detalleProductoCollection;
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