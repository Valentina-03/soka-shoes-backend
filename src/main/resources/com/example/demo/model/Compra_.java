package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.863-0500")
@StaticMetamodel(Compra.class)
public class Compra_ {
	public static volatile SingularAttribute<Compra, Long> idCompra;
	public static volatile SingularAttribute<Compra, Integer> totalCompra;
	public static volatile SingularAttribute<Compra, String> estado;
	public static volatile SingularAttribute<Compra, Usuario> usuario;
	public static volatile CollectionAttribute<Compra, Transaccionp> transaccionpCollection;
	public static volatile CollectionAttribute<Compra, DetalleCompra> detalleCompraCollection;
}
