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

	@Override
	public List<Producto> filtrar(List<List<String>> body) 
	{
		List<Producto> productos = new ArrayList<>();
		List<Integer> ids = new ArrayList<Integer>();
		if(body.get(0).isEmpty() && body.get(1).isEmpty() && body.get(4).isEmpty())
			ids = pDAO.getDisponibles();
		else ids = getProductosFilter(body);
		
		if(ids.isEmpty()) return productos;		
		if(!body.get(2).isEmpty()) ids = getDetallesFilter(ids, body.get(2));
		int min = -1, max = -1;
		if(!body.get(3).isEmpty()) { min = Integer.parseInt(body.get(3).get(0)); max = Integer.parseInt(body.get(3).get(1));}
		
		for(int i : ids) {
			DetalleProducto dp = dpDAO.findById(i).orElse(null);
			if(min != -1) {
				if(dp.getTalla().getNumero() >= min && dp.getTalla().getNumero() <= max)
					productos.add(dp.getProducto());
			}else productos.add(dp.getProducto()); 
		}
		return productos;
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
	
	private List<Integer> getProductosFilter(List<List<String>> ids){
		String query = "SELECT id_producto FROM producto WHERE estado = 1 AND (";
		boolean ant = false;
		
		List<String> aux = ids.get(0);	
		for(int i = 0; i<aux.size(); i++) {
			if(ant) query += " OR ";
			query += "marca = " + aux.get(i);
			ant = true;
		}		
		aux = ids.get(1);	
		for(int i = 0; i<aux.size(); i++) {
			if(ant) query += " OR ";
			query += "categoria = " + aux.get(i);
			ant = true;
		}
		
		aux = ids.get(4);
		if(aux.size() > 0) {
			if(ids.get(0).isEmpty() && ids.get(1).isEmpty())
				query += "precio >= " + aux.get(0) + " AND precio <= " + aux.get(1) + ")";
			else query += ") AND (precio >= " + aux.get(0) + " AND precio <= " + aux.get(1) + ")";
		}
		
		System.out.println(query);	
		return (List<Integer>) em.createNativeQuery(query).getResultList();
	}
	
	private List<Integer> getDetallesFilter(List<Integer> ids, List<String> colores){
		String query = "SELECT id_detalle FROM detalle_producto WHERE (";
		
		for(int i = 0; i<ids.size(); i++) {
			if(i!=0) query += " OR ";
			query += "producto = " + ids.get(i);
		} query += ") AND (";
		
		for(int i = 0; i<colores.size(); i++) {
			if(i!=0) query += " OR ";
			query += "color = '" + colores.get(i) + "'";
		}query += ") GROUP BY producto";
		
		System.out.println(query);	
		return (List<Integer>) em.createNativeQuery(query).getResultList();
	}
}