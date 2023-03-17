package com.example.demo.security.model;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.902-0500")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Integer> id_Usuario;
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SetAttribute<Usuario, Rol> roles;
	public static volatile CollectionAttribute<Usuario, Compra> compraCollection;
	public static volatile CollectionAttribute<Usuario, Carrito> carritoCollection;
}
