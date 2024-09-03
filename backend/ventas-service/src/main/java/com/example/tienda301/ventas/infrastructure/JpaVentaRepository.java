package com.example.tienda301.ventas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tienda301.ventas.domain.Venta;

public interface JpaVentaRepository extends JpaRepository<Venta, Long> {
}
