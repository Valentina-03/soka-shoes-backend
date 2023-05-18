package com.example.demo.service.imp;

import com.example.demo.dao.DetalleProductoDAO;
import com.example.demo.model.DetalleProducto;
import com.example.demo.service.DetalleProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleProductoImp implements DetalleProductoService{
    
    @Autowired
    DetalleProductoDAO dpDAO;

    @Override
    @Transactional
    public void guardar(DetalleProducto detalleProducto) {
    	dpDAO.save(detalleProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleProducto> listar() {
        return dpDAO.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public Optional<DetalleProducto> encontrar(Integer id) {
		return dpDAO.findById(id);
	}

	@Override
	@Transactional
	public void eliminar(Integer id) {
		dpDAO.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetalleProducto> listarProductosPorProdCol(Integer p, String c) {
		List<DetalleProducto> productos = new ArrayList<DetalleProducto>();
		List<Integer> ids = dpDAO.getProductsByProdCol(p, c);
		for(int i : ids)
			productos.add(dpDAO.findById(i).orElse(null));
		return productos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetalleProducto> listarProductosPorProdTal(Integer p, Integer t) {
		List<DetalleProducto> productos = new ArrayList<DetalleProducto>();
		List<Integer> ids = dpDAO.getProductsByProdTal(p, t);
		for(int i : ids)
			productos.add(dpDAO.findById(i).orElse(null));
		return productos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetalleProducto> listarProductosPorColTal(String c, Integer t) {
		List<DetalleProducto> productos = new ArrayList<DetalleProducto>();
		List<Integer> ids = dpDAO.getProductsByColTal(c, t);
		for(int i : ids)
			productos.add(dpDAO.findById(i).orElse(null));
		return productos;
	}

	@Override
	@Transactional(readOnly = true)
	public DetalleProducto listarProductosPorTodo(Integer p, String c, Integer t) {
		Integer id = dpDAO.getProductoByAll(p, c, t);
		System.out.println(id);
		if(id == null) return null;
		return dpDAO.findById(id).orElse(null);
	}    
}