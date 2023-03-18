package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleProducto.findAll", query = "SELECT c FROM DetalleProducto c"),
    @NamedQuery(name = "DetalleProducto.findByIdDetalleProducto", query = "SELECT c FROM DetalleProducto c WHERE c.idDetalle = :idDetalle"),
    @NamedQuery(name = "DetalleProducto.findByNombre", query = "SELECT c FROM Color c WHERE c.nombre = :nombre")})

public class DetalleProducto implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private String idDetalle;
    
    @Column(name= "url_img")
    private String img;
    
    @Column(name= "cantidad")
    private int cantidad;
    
    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "id_producto")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "color", referencedColumnName = "id_color")    
    private Color color;
    
    @ManyToOne
    @JoinColumn(name = "talla", referencedColumnName = "id_talla")    
    private Talla talla;
    
    @OneToMany(mappedBy = "detalleProducto")
    @JsonIgnore
    private Collection<DetalleCompra> detalleCompraCollection;
    
    
    public DetalleProducto() {}

    public DetalleProducto(String idDetalle) {
        this.idDetalle = idDetalle;
    }
    

   public String getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(String idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Collection<DetalleCompra> getDetalleCompraCollection() {
		return detalleCompraCollection;
	}

	public void setDetalleCompraCollection(Collection<DetalleCompra> detalleCompraCollection) {
		this.detalleCompraCollection = detalleCompraCollection;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetalleProducto))
            return false;
        DetalleProducto other = (DetalleProducto) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.DetalleProducto[ idDetalle=" + idDetalle + " ]";
    }    
}