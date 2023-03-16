/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import com.example.demo.model.Producto;
import com.example.demo.negocio.SokaShoes;
import com.example.demo.security.model.Rol;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Santi & Dani
 */
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioRest {

    @Autowired
    UsuarioService user;

    SokaShoes nexp = new SokaShoes();

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        return ResponseEntity.ok(user.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Usuario u, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        user.guardar(u);
        return ResponseEntity.ok(u);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Usuario u, BindingResult br){
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = user.encontrar(u.getId_Usuario()).orElse(null);
        if(usuario ==null){
            return new ResponseEntity("Usuario no existe",HttpStatus.NOT_FOUND);
        }
        user.guardar(u);

        return ResponseEntity.ok(user.encontrar(u.getId_Usuario()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable int id) {

        Usuario u = user.encontrar(id).orElse(null);
        if(u == null){
            return new ResponseEntity("El complemento no fue encontrado", HttpStatus.NOT_FOUND);
        }
        user.eliminar(id);

        return ResponseEntity.ok(u);
    }
  
    @GetMapping(path = "/{id}/compras")
    public ResponseEntity<List<Compra>> paquetesPorUsuario(@PathVariable int id){
        return ResponseEntity.ok((List)user.encontrar(id).get().compraCollection());
    }

    @GetMapping(path = "/{id}/carrito")
    public ResponseEntity<List<Carrito>> carritoDeUsuario(@PathVariable int id){
        return ResponseEntity.ok((List)user.encontrar(id).get().carritoCollection());
    }


    @GetMapping(path = "/{username}/username")
    public ResponseEntity<?> usuarioPorUsername(@PathVariable String username){
        Usuario u = user.getByNombreUsuario(username).orElse(null);

        if(u==null){
            return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(u);
    }

    @GetMapping(path = "/cantidadclientes")
    public ResponseEntity<?> getClientesRegistrados() {
        List<Usuario> usuarios = user.listar();
        int cantidad = 0;
        //Rol robj = new Rol(Rol.RolNombre.ROLE_ADMIN);
        for (int i = 0; i < usuarios.size(); i++) {
            //Set<Rol> roles = usuarios.get(i).getRoles();
           if(!usuarios.get(i).getUsername().equals("Admin")){
               cantidad++;
           }
        }
        return ResponseEntity.ok(cantidad);
    }

    
    
}
