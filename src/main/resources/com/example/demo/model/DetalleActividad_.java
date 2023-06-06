package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-05-27T23:25:04.871-0500")
@StaticMetamodel(DetalleActividad.class)
public class DetalleActividad_ {
	public static volatile SingularAttribute<DetalleActividad, Integer> idDetalle;
	public static volatile SingularAttribute<DetalleActividad, Actividad> actividad;
	public static volatile SingularAttribute<DetalleActividad, Estudiante> estudiante;
	public static volatile SingularAttribute<DetalleActividad, Double> calificacion;
	public static volatile SingularAttribute<DetalleActividad, String> estado;
	public static volatile SingularAttribute<DetalleActividad, String> observacion;
}
