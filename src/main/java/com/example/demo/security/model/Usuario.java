package com.example.demo.security.model;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Usuario;
    
    @NotNull
    @Column(unique = true)
    private String username;
    
    @NotNull
    private String email;
    
    @NotNull
    private String password;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "usuario")
    private Collection<Compra> compraCollection;
    
    @OneToMany(mappedBy = "usuario")
    private Collection<Carrito> carritoCollection;

    public Usuario() {
    }

    public Usuario(int id_Usuario, String nombreUsuario, @NotNull String email, @NotNull String password) {

        this.username = nombreUsuario;
        this.email = email;
        this.password = password;
        this.id_Usuario = id_Usuario;
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

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
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

    public Collection<Compra> compraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public Collection<Carrito> carritoCollection() {
        return carritoCollection;
    }

    public void setCarritoCollection(Collection<Carrito> carritoCollection) {
        this.carritoCollection = carritoCollection;
    }
    @Override
    public String toString() {
        return "com.example.demo.security.entity.Usuario[ idUsuario=" + id_Usuario + " ]";
    }
}