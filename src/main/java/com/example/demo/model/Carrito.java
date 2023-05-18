package com.example.demo.model;

import com.example.demo.security.model.Usuario;

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
@Table(name = "carrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c"),
    @NamedQuery(name = "Carrito.findByIdCarrito", query = "SELECT c FROM Carrito c WHERE c.idCarrito = :idCarrito"),})

public class Carrito implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito") 
    private Long idCarrito;
    
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @JoinColumn(name = "detalle_producto", referencedColumnName = "id_detalle")
    @ManyToOne
    private DetalleProducto detalle;
    
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne    
    private Usuario usuario;

    public Carrito() {}

    public Carrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleProducto getDetalleProducto() {
        return detalle;
    }

    public void setDetalleProducto(DetalleProducto detalle) {
        this.detalle = detalle;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrito != null ? idCarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.idCarrito == null && other.idCarrito != null) || (this.idCarrito != null && !this.idCarrito.equals(other.idCarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Carrito[ idCarrito=" + idCarrito + " ]";
    }
    
}