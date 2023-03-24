package com.example.demo.security.model;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import com.example.demo.model.Direccion;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "usuario")
    private Collection<Direccion> direccionCollection;
    
    @OneToMany(mappedBy = "usuario")
    private Collection<Compra> compraCollection;
    
    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    @JsonIgnore
    private Collection<Carrito> carritoCollection;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {}

    public Usuario(int id_Usuario, String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.username = nombreUsuario;
        this.email = email;
        this.password = password;
        this.idUsuario = id_Usuario;
    }

    public Usuario(@NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.username = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id_Usuario) {
        this.idUsuario = id_Usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public Collection<Direccion> getDireccionCollection() {
		return direccionCollection;
	}

	public void setDireccionCollection(Collection<Direccion> direccionCollection) {
		this.direccionCollection = direccionCollection;
	}

	public Collection<Compra> getCompraCollection() {
		return compraCollection;
	}

	public Collection<Carrito> getCarritoCollection() {
		return carritoCollection;
	}
	
	public void setCarritoCollection (Collection<Carrito> carritoCollection) {
        this.carritoCollection = carritoCollection;
    }

	@Override
    public String toString() {
        return "com.example.demo.security.entity.Usuario[ idUsuario=" + idUsuario + " ]";
    }
}