package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "actividad")
public class Actividad {
	
	@Id
	@Column(name = "id_actividad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idActividad;
	
	@JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
	@ManyToOne
    private TipoActividad tipoActividad;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "observacion")
	private String observacion;
	
}