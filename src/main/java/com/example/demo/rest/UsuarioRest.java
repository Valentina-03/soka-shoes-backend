package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import com.example.demo.model.DetalleProducto;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CarritoService;

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
    private CarritoService carservice;

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        Usuario usuario = service.encontrar(id).orElse(null);
        if(usuario == null) return new ResponseEntity<>("Usuario no existente", HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Usuario u, BindingResult br) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(u);
        return ResponseEntity.ok(u);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Usuario u, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = service.encontrar(u.getIdUsuario()).orElse(null);
        if(usuario == null) return new ResponseEntity<>("Usuario no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(u);
        return ResponseEntity.ok(service.encontrar(u.getIdUsuario()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Usuario u = service.encontrar(id).orElse(null);
        if(u == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);
        return ResponseEntity.ok(u);
    }
  
    @GetMapping(path = "/{id}/compras")
    public ResponseEntity<List<Compra>> comprasPorUsuario(@PathVariable Integer id)
    {
        return ResponseEntity.ok((List<Compra>)service.encontrar(id).get().getCompraCollection());
    }

    @GetMapping(path = "/{id}/carrito")
    public ResponseEntity<List<Carrito>> carritoDeUsuario(@PathVariable int id){
        return ResponseEntity.ok((List<Carrito>)service.encontrar(id).get().getCarritoCollection());
    }
    
    @PostMapping(path = "/{id}/addCarrito")
    public ResponseEntity<?> addCarrito(@PathVariable int id, @RequestBody DetalleProducto detalle){
    	Usuario user = service.encontrar(id).orElse(null);
    	if(user == null) 
    		return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
    	for(Carrito x: user.getCarritoCollection()) {
    		if(x.getDetalleProducto().equals(detalle)) {
    			x.setCantidad(x.getCantidad()+1);
    			carservice.guardar(x);
    	    	return ResponseEntity.ok(x);
    		}
    	}
    	
    	Carrito c = new Carrito();
        c.setCantidad(1); c.setDetalleProducto(detalle); c.setUsuario(user);
        carservice.guardar(c);    	
        return ResponseEntity.ok(c);
    }
    
    @PostMapping(path = "/{id}/editarCarritos")
    public ResponseEntity<?> editarCarritos(@PathVariable int id, @RequestBody Carrito[] carritos){
    	Usuario user = service.encontrar(id).orElse(null);
    	if(user == null) 
    		return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	for(Carrito c: carritos)
    		carservice.guardar(c); 
    	return ResponseEntity.ok(true);
    }
    
    @DeleteMapping(path = "/{id}/{idcarrito}/eliminar")
    public ResponseEntity<?> eliminarCarrito(@PathVariable int id, @PathVariable long idcarrito){
    	Usuario user = service.encontrar(id).orElse(null);
    	if(user == null) 
    		return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);

    	carservice.eliminar(idcarrito);
    	return ResponseEntity.ok(true);
    }

    @GetMapping(path = "/{email}/email")
    public ResponseEntity<?> usuarioPorEmail(@PathVariable String email)
    {
        Usuario u = service.getByEmail(email).orElse(null);
        if(u == null) return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(u);
    }

    @GetMapping(path = "/cantidadClientes")
    public ResponseEntity<?> getClientesRegistrados() 
    {
        return ResponseEntity.ok(service.listar().size());
    }    
    
}