package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compra.findByTotalCompra", query = "SELECT c FROM Compra c WHERE c.totalCompra = :totalCompra")})

public class Compra implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_compra")
    private Long idCompra;
    
    @Column(name = "total_compra")
    private Integer totalCompra;
    
    @Column(name = "estado")
    private String estado;
    
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;
    
    @OneToMany(mappedBy = "referenceSale")
    private Collection<Transaccionp> transaccionpCollection;
    
    @OneToMany(mappedBy = "compra")
    private Collection<DetalleCompra> detalleCompraCollection;

    public Compra() {
    }

    public Compra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getTotalCompra() {
        return totalCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotalCompra(Integer totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Transaccionp> transaccionpCollection() {
        return transaccionpCollection;
    }

    public void setTransaccionpCollection(Collection<Transaccionp> transaccionpCollection) {
        this.transaccionpCollection = transaccionpCollection;
    }

    public Collection<DetalleCompra> detalleCompraCollection() {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
