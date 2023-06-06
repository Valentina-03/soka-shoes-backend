package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-05-27T23:25:04.874-0500")
@StaticMetamodel(TipoActividad.class)
public class TipoActividad_ {
	public static volatile SingularAttribute<TipoActividad, Integer> idTipo;
	public static volatile SingularAttribute<TipoActividad, String> nombre;
	public static volatile CollectionAttribute<TipoActividad, Actividad> actividades;
}
