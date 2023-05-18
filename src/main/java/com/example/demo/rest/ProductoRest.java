package com.example.demo.rest;

import com.example.demo.model.DetalleProducto;
import com.example.demo.model.Producto;
import com.example.demo.model.ProductoDeatallesDto;
import com.example.demo.model.ProductoDetallesRequest;
import com.example.demo.model.Talla;
import com.example.demo.service.DetalleProductoService;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*")
public class ProductoRest 
{
    @Autowired
    private ProductoService service;
    
    @Autowired
    private DetalleProductoService detalle_service;
    
    
    @GetMapping("/{id}/disponible/{color}/{talla}/{cantidad}")
    public ResponseEntity<?> getDetalleDisponible(@PathVariable Integer id, @PathVariable String color, @PathVariable Integer talla, @PathVariable Integer cantidad)
    {
    	DetalleProducto d = detalle_service.listarProductosPorTodo(id, color, talla);
    	if(d != null && d.getCantidad() >= cantidad)
    		return ResponseEntity.ok(d);
    	
    	return ResponseEntity.ok(null);
    }
    
    @PostMapping("/filtrar")
    public ResponseEntity<?> getProductosByFiltro(@RequestBody List<List<String>> ids){
    	return ResponseEntity.ok(service.filtrar(ids));
    }
    
    @GetMapping("/{id}/detalles")
    public ResponseEntity<?> getDetalles(@PathVariable Integer id){
    	return ResponseEntity.ok(service.obtenerDetalles(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Producto>> getProductosDisponibles(){
    	return ResponseEntity.ok(service.listarDisponibles());
    }
    
    @GetMapping(path = "/cantidadDisponible")
    public ResponseEntity<?> getTotalProductos() 
    {
        List<Producto> productos = service.listar();
        long cantidad = 0;
        for (int i = 0; i < productos.size(); i++)
        	if(productos.get(i).getEstado())
        		cantidad += productos.get(i).getCantidad();
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping(path = "/{id}/deshabilitar")
    public ResponseEntity<?> deshabilitar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        p.setEstado(false); service.guardar(p);
        return ResponseEntity.ok(p);
    }
    
    @GetMapping(path = "/{id}/habilitar")
    public ResponseEntity<?> habilitar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        p.setEstado(true); service.guardar(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Producto>> get() {
    	return ResponseEntity.ok(service.listar());
    }

    @PostMapping()
    public ResponseEntity<?> guardar(@RequestBody ProductoDetallesRequest request) {
    	Producto producto = request.getProducto(); producto.setEstado(true);
    	service.guardar(producto);
    	List<ProductoDeatallesDto> detalles = request.getDetalles();
    	int cnt = 0;
    	
    	for(ProductoDeatallesDto i: detalles) {
    		for(Pair<Talla, Integer> t: i.getTallas()) {
    			DetalleProducto d = detalle_service.listarProductosPorTodo(producto.getIdProducto(), i.getColor().getIdColor(), t.getFirst().getIdTalla());
    			if(detalle_service.listarProductosPorTodo(producto.getIdProducto(), i.getColor().getIdColor(), t.getFirst().getIdTalla()) == null) {
    				d = new DetalleProducto();
    				
    				d.setColor(i.getColor());
    				d.setTalla(t.getFirst());
    				d.setCantidad(i.getCantidad());
    				d.setImg(i.getUrl());
    				d.setProducto(producto);   				
    			}else 
    				d.setCantidad(i.getCantidad());
    			
    			detalle_service.guardar(d);
    			cnt+=i.getCantidad();
    		}
    	}
    	producto.setCantidad(cnt);
    	service.guardar(producto);
        return ResponseEntity.ok(producto);
    }
    
    @PutMapping()
    public ResponseEntity<?> editar(@RequestBody ProductoDetallesRequest request) 
    {
    	Producto producto = service.encontrar(request.getProducto().getIdProducto()).orElse(null);
        if(producto == null) return new ResponseEntity<>("Producto no existe",HttpStatus.NOT_FOUND);
        producto.setCategoria(request.getProducto().getCategoria()); producto.setMarca(request.getProducto().getMarca());
        producto.setDescripcion(request.getProducto().getDescripcion()); producto.setNombre(request.getProducto().getNombre());
        producto.setPrecio(request.getProducto().getPrecio()); producto.setUpdatedAt(new Date()); 
        
        List<ProductoDeatallesDto> detalles = request.getDetalles(); int cnt = 0;
        
    	for(ProductoDeatallesDto i: detalles) {
    		for(Pair<Talla, Integer> t: i.getTallas()) {
    			DetalleProducto d = detalle_service.listarProductosPorTodo(i.getIdProducto(), i.getColor().getIdColor(), t.getFirst().getIdTalla());
    			if(d == null) {
    				d = new DetalleProducto();    				
    				d.setColor(i.getColor());
    				d.setTalla(t.getFirst());
    				d.setCantidad(t.getSecond());
    				d.setImg(i.getUrl());
    				d.setProducto(producto);   				
    			}else 
    				d.setCantidad(t.getSecond());   
    			detalle_service.guardar(d);
    			cnt+=t.getSecond();
    		}
    	}
    	producto.setCantidad(cnt);
        service.guardar(producto);
    	return ResponseEntity.ok(producto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if (p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(p);
    }
}