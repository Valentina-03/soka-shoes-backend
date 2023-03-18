package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByPerNom", query = "SELECT p FROM Persona p WHERE p.perNom = :perNom"),
    @NamedQuery(name = "Persona.findBySdoNom", query = "SELECT p FROM Persona p WHERE p.sdoNom = :sdoNom"),
    @NamedQuery(name = "Persona.findByPerApell", query = "SELECT p FROM Persona p WHERE p.perApell = :perApell"),
    @NamedQuery(name = "Persona.findBySdoApell", query = "SELECT p FROM Persona p WHERE p.sdoApell = :sdoApell"),
    @NamedQuery(name = "Persona.findByFechaNac", query = "SELECT p FROM Persona p WHERE p.fechaNac = :fechaNac"),
    @NamedQuery(name = "Persona.findByCelular", query = "SELECT p FROM Persona p WHERE p.celular = :celular"),})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_persona")
    private Integer idPersona;
    
    @JoinColumn(name = "tipo_id", referencedColumnName = "id_tipo")
    @ManyToOne
    private TipoIdentificacion tipoId;
    
    @Column(name = "per_nom")
    private String perNom;
    
    @Column(name = "sdo_nom")
    private String sdoNom;
    
    @Column(name = "per_apell")
    private String perApell;
    
    @Column(name = "sdo_apell")
    private String sdoApell;
    
    @Column(name = "fecha_nac") 
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    
    @Column(name = "celular")
    private String celular;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")    
    private Usuario usuario;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getPerNom() {
        return perNom;
    }

    public void setPerNom(String perNom) {
        this.perNom = perNom;
    }

    public String getSdoNom() {
        return sdoNom;
    }

    public void setSdoNom(String sdoNom) {
        this.sdoNom = sdoNom;
    }

    public String getPerApell() {
        return perApell;
    }

    public void setPerApell(String perApell) {
        this.perApell = perApell;
    }

    public String getSdoApell() {
        return sdoApell;
    }

    public void setSdoApell(String sdoApell) {
        this.sdoApell = sdoApell;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String cel) {
        this.celular = cel;
    }

    public TipoIdentificacion getTipoId() {
        return tipoId;
    }

    public void setTipoId(TipoIdentificacion idtipoId) {
        this.tipoId = idtipoId;
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
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Persona[ idPersona=" + idPersona + " ]";
    }
    
}