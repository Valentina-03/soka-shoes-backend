package com.example.demo.service.imp;

import com.example.demo.dao.DetalleProductoDAO;
import com.example.demo.dao.ProductoDAO;
import com.example.demo.model.DetalleProducto;
import com.example.demo.model.Producto;
import com.example.demo.model.ProductoDeatallesDto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	public List<ProductoDeatallesDto> obtenerDetalles(Integer id) 
	{
		List<ProductoDeatallesDto> detallesdto = new ArrayList<>();
		Producto p = pDAO.findById(id).orElse(null);
		List<DetalleProducto> detalles = (List<DetalleProducto>) p.getDetalleProductoCollection();
		
		for(DetalleProducto i: detalles){
			if(i.getCantidad() < 0) continue;		
			ProductoDeatallesDto aux = new ProductoDeatallesDto();
			aux.setIdProducto(p.getIdProducto());
			aux.setColor(i.getColor());
			
			int it = detallesdto.indexOf(aux);
			if(it == -1) {
				aux.setUrl(i.getImg());
				aux.addTalla(i.getTalla(), i.getCantidad());
				detallesdto.add(aux);
			}else
				detallesdto.get(it).addTalla(i.getTalla(), i.getCantidad());
		}
		
		return detallesdto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public DetalleProducto obtenerDetalle(Integer id, String color, Integer talla, Integer cantidad) 
	{
		Integer d = dpDAO.getProductoByAll(id, color, talla);
		DetalleProducto detalle = dpDAO.findById(d).orElse(null);
		return detalle;
	}

	@Override
	public List<Producto> filtrar(List<List<String>> body) 
	{
		List<Producto> productos = new ArrayList<>();
		List<Integer> ids = new ArrayList<Integer>();
		boolean first = !body.get(0).isEmpty() || !body.get(1).isEmpty() || !body.get(4).isEmpty();
		
		if(first) ids = getProductosFilter(body, true);
		else ids = getProductosFilter(body, false);
		if(ids.isEmpty()) return productos;	
		ids = getDetallesFilter(ids, body.get(2));
		
		Set<Integer> set = new HashSet<>();
		int min = -1, max = -1;
		if(!body.get(3).isEmpty()) { min = Integer.parseInt(body.get(3).get(0)); max = Integer.parseInt(body.get(3).get(1));}
		
		for(Integer i : ids) {
			DetalleProducto dp = dpDAO.findById(i).orElse(null);
			if(min != -1) {
				if(dp.getTalla().getNumero() >= min && dp.getTalla().getNumero() <= max)
					if(!set.contains(dp.getProducto().getIdProducto())) {
						productos.add(dp.getProducto());
						set.add(dp.getProducto().getIdProducto());
					}
			}else if(!set.contains(dp.getProducto().getIdProducto())) {
				productos.add(dp.getProducto());
				set.add(dp.getProducto().getIdProducto());
			}
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
	
	private List<Integer> getProductosFilter(List<List<String>> ids, boolean r){
		String query = "SELECT id_producto FROM producto WHERE estado = 1";
		if(r == false) return (List<Integer>) em.createNativeQuery(query).getResultList();
		
		List<String> aux = ids.get(0);
		if(!aux.isEmpty()) query += " AND (";
		for(int i = 0; i<aux.size(); i++) {
			if(i>0) query += " OR ";
			query += "marca = " + aux.get(i);
			if(i==aux.size()-1) query += ")";
		}		
		aux = ids.get(1);
		if(!aux.isEmpty()) query += " AND (";
		for(int i = 0; i<aux.size(); i++) {
			if(i>0) query += " OR ";
			query += "categoria = " + aux.get(i);
			if(i==aux.size()-1) query += ")";
		}
		
		aux = ids.get(4);
		if(!aux.isEmpty())
			query += " AND (precio >= " + aux.get(0) + " AND precio <= " + aux.get(1) + ")";
		
		return (List<Integer>) em.createNativeQuery(query).getResultList();
	}
	
	private List<Integer> getDetallesFilter(List<Integer> ids, List<String> colores)
	{
		String query = "SELECT id_detalle FROM detalle_producto";
		
		if(!ids.isEmpty() || !colores.isEmpty()) query += " WHERE  (";		
		for(int i = 0; i<ids.size(); i++) {
			if(i!=0) query += " OR ";
			query += "producto = " + ids.get(i);
			if(i==ids.size()-1) query += ")";
		} 
		
		if(!colores.isEmpty() && !ids.isEmpty()) query += "AND (";		
		for(int i = 0; i<colores.size(); i++) {
			if(i!=0) query += " OR ";
			query += "color = '" + colores.get(i) + "'";
			if(i==colores.size()-1) query += ")";
		}
		query += " ORDER BY id_detalle";		
		return (List<Integer>) em.createNativeQuery(query).getResultList();
	}
}