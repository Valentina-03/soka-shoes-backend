package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import com.example.demo.model.Persona;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.PersonaService;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRest 
{
    @Autowired
    private UsuarioService service;
    
    @Autowired
    private PersonaService persona_service;

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Usuario u, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(u);
        return ResponseEntity.ok(u);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Usuario u, BindingResult br)
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = service.encontrar(u.getIdUsuario()).orElse(null);
        if(usuario == null)
        	return new ResponseEntity<>("Usuario no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(u);

        return ResponseEntity.ok(service.encontrar(u.getIdUsuario()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Usuario u = service.encontrar(id).orElse(null);
        if(u == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        Persona p = persona_service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        persona_service.eliminar(p.getIdPersona());
        service.eliminar(id);
        return ResponseEntity.ok(u);
    }
  
    @GetMapping(path = "/{id}/compras")
    public ResponseEntity<List<Compra>> paquetesPorUsuario(@PathVariable Integer id)
    {
        return ResponseEntity.ok((List<Compra>)service.encontrar(id).get().getCompraCollection());
    }

    @GetMapping(path = "/{id}/carrito")
    public ResponseEntity<List<Carrito>> carritoDeUsuario(@PathVariable int id)
    {
        return ResponseEntity.ok((List<Carrito>)service.encontrar(id).get().getCarritoCollection());
    }

    @GetMapping(path = "/{username}/username")
    public ResponseEntity<?> usuarioPorUsername(@PathVariable String username)
    {
        Usuario u = service.getByNombreUsuario(username).orElse(null);
        if(u==null)
            return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(u);
    }

    @GetMapping(path = "/cantidadclientes")
    public ResponseEntity<?> getClientesRegistrados() 
    {
        return ResponseEntity.ok(service.listar().size());
    }    
    
}