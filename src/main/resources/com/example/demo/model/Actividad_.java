package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-05-27T23:25:04.868-0500")
@StaticMetamodel(Actividad.class)
public class Actividad_ {
	public static volatile SingularAttribute<Actividad, Integer> idActividad;
	public static volatile SingularAttribute<Actividad, TipoActividad> tipoActividad;
	public static volatile SingularAttribute<Actividad, String> nombre;
	public static volatile SingularAttribute<Actividad, String> descripcion;
	public static volatile SingularAttribute<Actividad, String> observacion;
}
