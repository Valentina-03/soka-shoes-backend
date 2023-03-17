package com.example.demo.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.867-0500")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ {
	public static volatile SingularAttribute<DetalleCompra, Integer> idDetalle;
	public static volatile SingularAttribute<DetalleCompra, Date> fecha;
	public static volatile SingularAttribute<DetalleCompra, Compra> compra;
	public static volatile SingularAttribute<DetalleCompra, Producto> producto;
}
