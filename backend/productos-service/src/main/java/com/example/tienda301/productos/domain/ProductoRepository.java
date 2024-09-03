package com.example.tienda301.productos.domain;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
	List<Producto> findAll();

	Optional<Producto> findById(Long id);

	Producto save(Producto producto);

	void deleteById(Long id);
}
