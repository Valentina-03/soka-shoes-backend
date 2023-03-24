package com.example.demo.service.imp;

import com.example.demo.dao.DetalleCompraDAO;
import com.example.demo.model.Carrito;
import com.example.demo.model.DetalleCompra;
import com.example.demo.model.DetalleProducto;
import com.example.demo.service.DetalleCompraService;
import com.example.demo.service.ProductoService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleCompraImp implements DetalleCompraService{
    
    @Autowired
    DetalleCompraDAO dcDAO;

    @Autowired
    ProductoService pser;
    
    @Override
    @Transactional
    public void guardar(DetalleCompra detalleCompra) {
        dcDAO.save(detalleCompra);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<DetalleCompra> encontrar(int id) {
        return dcDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleCompra> listar() {
        return dcDAO.findAll();
    }
    
    @Override
    @Transactional
    public void eliminar(Integer id) {
    	dcDAO.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public List<DetalleCompra> obtenerPorCarrito(Collection<Carrito> carrito) 
	{
		List<DetalleCompra> detalles = new ArrayList<>();
		
		for(Carrito i: carrito) 
		{
			DetalleProducto aux = i.getDetalleProducto();
			if(pser.obtenerDetalle(aux.getProducto().getIdProducto(), aux.getColor().getIdColor(), aux.getTalla().getIdTalla(), i.getCantidad()) == null) return null;
			
			DetalleCompra d = new DetalleCompra();
			d.setDetalleProducto(aux);
			d.setCantidad(i.getCantidad());
			d.setPrecioDet(aux.getProducto().getPrecio());
			d.setPrecioTot(aux.getProducto().getPrecio()*i.getCantidad());
			
			detalles.add(d);
		}
		
		return detalles;
		
	}
    
}
