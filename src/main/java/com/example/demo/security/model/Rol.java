package com.example.demo.security.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rol")
public class Rol 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int idRol;

    public enum RolNombre {
    	ROLE_ADMIN, ROLE_USER
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Rol(@NotNull RolNombre rolNombre, int id) {
        this.rolNombre = rolNombre;
        this.idRol = id;
    }

    public int getId() {
        return idRol;
    }

    public void setId(int id) {
        this.idRol = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
