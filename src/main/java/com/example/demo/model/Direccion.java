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

import com.example.demo.security.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
    @NamedQuery(name = "Direccion.findByidDireccion", query = "SELECT d FROM Direccion d WHERE d.idDireccion = :idDireccion")})
public class Direccion implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "info_adic")
    private String infoAdic;
    
    @Column(name = "cod_postal")
    private String codPostal;
    
    @JoinColumn(name = "ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad ciudad;
    
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    
    public Direccion() {}

    public Direccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Direccion))
            return false;
        Direccion other = (Direccion) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Categoria[ idDireccion=" + idDireccion + " ]";
    }

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getInfoAdic() {
		return infoAdic;
	}

	public void setInfoAdic(String infoAdic) {
		this.infoAdic = infoAdic;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}