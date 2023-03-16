package com.example.demo.negocio;

import com.example.demo.security.model.Rol;

import java.util.HashSet;
import java.util.Set;

public class PlayGround {
    public static void main(String[] args) {
        SokaShoes norteXploradores =new SokaShoes();
        System.out.println(norteXploradores.convertirFecha("2021-09-13 23:56:49","-"));

        Set<Rol> roles = new HashSet<>();
        roles.add(new Rol(Rol.RolNombre.ROLE_USER,1));
        roles.add(new Rol(Rol.RolNombre.ROLE_ADMIN,2));
        System.out.println(roles.toString());
        System.out.println(roles.contains(new Rol(Rol.RolNombre.ROLE_USER)));

    }
}
