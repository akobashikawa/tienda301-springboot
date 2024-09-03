package com.example.tienda301.productos.application;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoUpdateDTO {
	private String nombre;
	private BigDecimal precio;
	private Integer cantidad;
}
