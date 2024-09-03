package com.example.tienda301.ventas.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.productos.domain.Producto;

@Entity
@Table(name = "ventas")
@Data
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "persona_id", nullable = false)
	private Persona persona;

	private BigDecimal precio;
	
	private Integer cantidad;
	
	@Column(name = "fecha", nullable = false, updatable = false)
	private LocalDate fecha = LocalDate.now();

}
