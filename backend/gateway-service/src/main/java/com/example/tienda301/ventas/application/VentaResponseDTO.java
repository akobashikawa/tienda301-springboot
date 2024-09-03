package com.example.tienda301.ventas.application;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.productos.domain.Producto;

@Data
public class VentaResponseDTO {
	private Long id;
	private Long producto_id;
	private Producto producto;
	private Long persona_id;
	private Persona persona;
	private BigDecimal precio;
	private int cantidad;
	private LocalDate fecha;
}
