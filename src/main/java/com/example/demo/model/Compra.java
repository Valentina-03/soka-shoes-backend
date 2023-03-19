package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra")})
public class Compra implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "total_compra")
    private Double totalCompra;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToMany(mappedBy = "compra")
    private Collection<DetalleCompra> detalleCompraCollection;
    
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;
    
    @JoinColumn(name = "direccion", referencedColumnName = "id_direccion")
    @ManyToOne
    private Direccion direccion;
    
    @OneToMany(mappedBy = "referenceSale")
    @JsonIgnore
    private Collection<Transaccionp> transaccionpCollection;
    
    @PrePersist
    private void prePersist(){
    	this.fecha = new Date();
    }
    
    public Compra() {}

    public Compra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }
    
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Collection<Transaccionp> getTransaccionpCollection() {
        return transaccionpCollection;
    }

    public void setTransaccionpCollection(Collection<Transaccionp> transaccionpCollection) {
        this.transaccionpCollection = transaccionpCollection;
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
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Compra[ idCompra=" + idCompra + " ]";
    }
    
}