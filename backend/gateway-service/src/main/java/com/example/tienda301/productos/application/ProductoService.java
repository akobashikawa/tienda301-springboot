package com.example.tienda301.productos.application;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tienda301.productos.domain.Producto;
import com.example.tienda301.ventas.domain.Venta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

	private final RestTemplate restTemplate;

	// URL del microservicio de productos
	private final String productoServiceUrl = "http://localhost:8081/api/productos";

	public ProductoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Producto> getItems() {
		Producto[] productos = restTemplate.getForObject(productoServiceUrl, Producto[].class);
		return Arrays.asList(productos);
	}

	public Optional<Producto> getItemById(Long id) {
		String url = productoServiceUrl + "/" + id;
		Producto producto = restTemplate.getForObject(url, Producto.class);
		return Optional.ofNullable(producto);
	}

	public Producto createItem(Producto producto) {
		return restTemplate.postForObject(productoServiceUrl, producto, Producto.class);
	}

	public Optional<Producto> updateItem(Long id, Producto producto) {
        String url = productoServiceUrl + "/" + id;
        restTemplate.put(url, producto);
        return Optional.of(getItemById(id).orElse(null));  // Retornar el item actualizado
    }

	public void deleteItemById(Long id) {
		String url = productoServiceUrl + "/" + id;
		restTemplate.delete(url);
	}
}
