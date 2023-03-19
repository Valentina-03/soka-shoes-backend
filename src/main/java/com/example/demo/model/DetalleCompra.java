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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCompra.findAll", query = "SELECT d FROM DetalleCompra d"),
    @NamedQuery(name = "DetalleCompra.findByIdDetalle", query = "SELECT d FROM DetalleCompra d WHERE d.idDetalle = :idDetalle")})
public class DetalleCompra implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @Column(name = "precio_det")
    private Double precioDet;
    
    @Column(name = "precio_tot")
    private Double precioTot;
    
    @JoinColumn(name = "compra", referencedColumnName = "id_compra")
    @ManyToOne
    @JsonIgnore
    private Compra compra;
    
    @JoinColumn(name = "detalle_producto", referencedColumnName = "id_detalle")
    @ManyToOne
    private DetalleProducto detalleProducto;

    
    public DetalleCompra() {}

    public DetalleCompra(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioDet() {
		return precioDet;
	}

	public void setPrecioDet(Double precioDet) {
		this.precioDet = precioDet;
	}

	public Double getPrecioTot() {
		return precioTot;
	}

	public void setPrecioTot(Double precioTot) {
		this.precioTot = precioTot;
	}

	public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetalleCompra)) {
            return false;
        }
        DetalleCompra other = (DetalleCompra) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.DetalleCompra[ idDetalle=" + idDetalle + " ]";
    }
    
}