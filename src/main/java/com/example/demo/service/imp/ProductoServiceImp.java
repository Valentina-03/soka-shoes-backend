package com.example.demo.service.imp;

import com.example.demo.dao.DetalleProductoDAO;
import com.example.demo.dao.ProductoDAO;
import com.example.demo.model.DetalleProducto;
import com.example.demo.model.Producto;
import com.example.demo.model.ProductoDeatllesDto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    ProductoDAO pDAO;
    
    @Autowired
    DetalleProductoDAO dpDAO;
    
    @PersistenceContext
    private EntityManager em;
    

    @Override
    @Transactional
    public void guardar(Producto producto) {
        pDAO.save(producto);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Producto> encontrar(int id) { 
    	return pDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return pDAO.findAll();
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        pDAO.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarDisponibles() {
		List<Producto> productos = new ArrayList<Producto>();
		List<Integer> ids = pDAO.getDisponibles();
		for(int i : ids)
			productos.add(pDAO.findById(i).orElse(null));
		
		return productos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> filtrar(Integer marca, Integer categoria, String color, Integer talla, Double precio_min, Double precio_max) 
	{
		List<Producto> productos = new ArrayList<>();
		List<Integer> ids = getIds(marca, categoria, color, talla, precio_min, precio_max);
		
		for(int i : ids)
			productos.add(pDAO.findById(i).orElse(null));
		return productos;			
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoDeatllesDto> obtenerDetalles(Integer id) 
	{
		List<ProductoDeatllesDto> detalles = new ArrayList<>();
		Producto p = pDAO.findById(id).orElse(null);
		List<DetalleProducto> d = (List<DetalleProducto>) p.getDetalleProductoCollection();
		
		for(DetalleProducto i: d){
			if(i.getCantidad() < 0) continue;			
			ProductoDeatllesDto aux = new ProductoDeatllesDto();
			aux.setIdDetalle(i.getIdDetalle());
			aux.setColor(i.getColor());
			int it = detalles.indexOf(aux);
			
			if(it == -1) {
				aux.setUrl(i.getImg());
				aux.addTalla(i.getTalla());
				detalles.add(aux);
			}else
				detalles.get(it).addTalla(i.getTalla());
		}
		
		return detalles;
	}
	
	private List<Integer> getIds(Integer marca, Integer categoria, String color, Integer talla, Double precio_min, Double precio_max){
		String query = "SELECT id_producto FROM producto WHERE ";
		boolean ant = false;
		if(marca != -1) {
			ant = true;
			query += "marca = " + marca;
		}
		if(categoria != -1) {
			if(ant) query += " AND "; else ant = true;
			query += "categoria = " + categoria;
		}
		if(!color.equals("-1")) {
			if(ant) query += " AND "; else ant = true;
			query += "color = '" + color + "'";
		}
		if(talla != -1) {
			if(ant) query += " AND "; else ant = true;
			query += "talla = " + talla;
		}
		if(precio_min != -1) {
			if(ant) query += " AND "; else ant = true;
			query += "precio >= " + precio_min + " AND precio <= " + precio_max;
		}
		return (List<Integer>) em.createNativeQuery(query).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public int obtenerDetalle(Integer id, String color, Integer talla, Integer cantidad) 
	{
		Integer d = dpDAO.getProductsByAll(id, color, talla);
		DetalleProducto detalle = dpDAO.findById(d).orElse(null);
		if(detalle == null) return -1;
		if(detalle.getCantidad() < cantidad) return 0;
		return detalle.getIdDetalle();
	}    
}