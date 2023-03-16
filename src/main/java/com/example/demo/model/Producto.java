package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByModelo", query = "SELECT p FROM Producto p WHERE p.modelo = :modelo"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca")})

public class Producto implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false) @Column(name = "id_producto")
    private Integer idProducto;
    
    @Column(name = "precio")
    private int precio;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Column(name = "url_img")
    private String urlImg;
    
    @Size(max = 25) @Column(name = "modelo")
    private String modelo;
    
    @Size(max = 50) @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "estado")
    private boolean estado;
    
    @OneToMany(mappedBy = "producto")
    private Collection<DetalleCompra> detalleCompraCollection;
    
    @JoinColumn(name = "categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria categoria;
    
    @JoinColumn(name = "marca", referencedColumnName = "id_marca")
    @ManyToOne
    private Marca marca;
    
    @ManyToMany(mappedBy = "productoCollection")
    private Collection<Color> colorCollection;
    
    @JoinTable(name = "producto_talla", joinColumns = {
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")}, inverseJoinColumns = {
        @JoinColumn(name = "id_talla", referencedColumnName = "id_talla")})
    @ManyToMany
    private Collection<Talla> tallaCollection;
    
    @OneToMany(mappedBy = "producto")
    private Collection<Carrito> carritoCollection;

    
    public Producto() {}

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Collection<Color> colorCollection() {
        return colorCollection;
    }

    public void setColorCollection(Collection<Color> colorCollection) {
        this.colorCollection = colorCollection;
    }

    public Collection<Talla> tallaCollection() {
        return tallaCollection;
    }

    public void setTallaCollection(Collection<Talla> tallaCollection) {
        this.tallaCollection = tallaCollection;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Collection<DetalleCompra> detalleCompraCollection() {
        return detalleCompraCollection;
    }

    public void setDetalleCompraCollection(Collection<DetalleCompra> detalleCompraCollection) {
        this.detalleCompraCollection = detalleCompraCollection;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Collection<Carrito> carritoCollection() {
        return carritoCollection;
    }

    public void setCarritoCollection(Collection<Carrito> carritoCollection) {
        this.carritoCollection = carritoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Producto[ idProducto=" + idProducto + " ]";
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
