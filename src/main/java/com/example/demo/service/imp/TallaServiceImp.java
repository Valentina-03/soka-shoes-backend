package com.example.demo.service.imp;

import com.example.demo.dao.ProductoDAO;
import com.example.demo.dao.TallaDAO;
import com.example.demo.model.Producto;
import com.example.demo.model.Talla;
import com.example.demo.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TallaServiceImp implements TallaService {

    @Autowired
    TallaDAO tDAO;
    
    @Autowired
    ProductoDAO pDAO;
    
    
    @Override
    @Transactional(readOnly = true )
	public List<Producto> getProductos(Integer id) {
    	List<Producto> productos = new ArrayList<Producto>();
		List<Integer> ids = tDAO.getProductsByTalla(id);
		for(int i : ids)
			productos.add(pDAO.findById(i).orElse(null));
		return productos;
	}

	@Override
	@Transactional(readOnly = true )
	public int getCntProductos(Integer id) {
		return tDAO.getProductsByTalla(id).size();
	}
	
    @Override
    @Transactional
    public void guardar(Talla talla) {
        tDAO.save(talla);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Talla> encontrar(Integer id) { return tDAO.findById(id);}

    @Override
    @Transactional(readOnly = true)
    public List<Talla> listar() {
        return tDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public void eliminar(Integer id) {
        tDAO.deleteById(id);
    }
}