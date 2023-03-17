package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.778-0500")
@StaticMetamodel(Carrito.class)
public class Carrito_ {
	public static volatile SingularAttribute<Carrito, Integer> idCarrito;
	public static volatile SingularAttribute<Carrito, Integer> cantidad;
	public static volatile SingularAttribute<Carrito, Producto> producto;
	public static volatile SingularAttribute<Carrito, Usuario> usuario;
}
