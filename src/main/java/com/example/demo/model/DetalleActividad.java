package com.example.demo.model;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_actividad")
public class DetalleActividad {
	
	@Id
	@Column(name = "id_detalle")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalle;
	
	@JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
	@ManyToOne
    private Actividad actividad;
	
	@JoinColumn(name = "id_estudiante", referencedColumnName = "codigo")
	@ManyToOne
    private Estudiante estudiante;
	
	@Column(name = "calificacion")
	private Double calificacion;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "observacion")
	private String observacion;
	
}