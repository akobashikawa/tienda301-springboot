package com.example.tienda301.productos.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tienda301.productos.domain.Producto;
//import com.example.tienda301.productos.domain.ProductoRepository;

@Service
public class ProductoService {

//	private final ProductoRepository productoRepository;
	private final RestTemplate restTemplate;

//	public ProductoService(ProductoRepository productoRepository) {
//		this.productoRepository = productoRepository;
//	}

	public ProductoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

//    public List<Producto> getItems() {
//        return productoRepository.findAll();
//    }

//	public Optional<Producto> getItemById(Long id) {
//        return productoRepository.findById(id);
//	}
	
	public Optional<Producto> getItemById(Long id) {
		String url = "http://localhost:8081/api/productos/" + id;
		try {
			Producto producto = restTemplate.getForObject(url, Producto.class);
			return Optional.ofNullable(producto); // Envuelve el resultado en un Optional
		} catch (Exception e) {
			return Optional.empty(); // En caso de error o si no se encuentra el item, devuelve Optional.empty()
		}
	}

//    public Producto createItem(Producto producto) {
//        return productoRepository.save(producto);
//    }

//    public Producto updateItem(Long id, Producto producto) {
//    	Producto found = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
//    	if (producto.getNombre() != null) {
//    		found.setNombre(producto.getNombre());
//    	}
//    	if (producto.getCantidad() != null) {
//    		found.setCantidad(producto.getCantidad());
//    	}
//    	if (producto.getPrecio() != null) {
//    		found.setPrecio(producto.getPrecio());
//    	}
//        return productoRepository.save(found);
//    }

	public Optional<Producto> updateItem(Long id, Producto producto) {
		String urlGet = "http://localhost:8081/api/productos/" + id;
		String urlUpdate = "http://localhost:8081/api/productos/" + id;

		// Obtener el producto existente
        Producto found = restTemplate.getForObject(urlGet, Producto.class);
        if (found == null) {
            return Optional.empty();
        }

        // Actualizar los campos necesarios
        if (producto.getNombre() != null) {
            found.setNombre(producto.getNombre());
        }
        if (producto.getCantidad() != null) {
            found.setCantidad(producto.getCantidad());
        }
        if (producto.getPrecio() != null) {
            found.setPrecio(producto.getPrecio());
        }

        // Enviar la actualización al microservicio de productos
        restTemplate.put(urlUpdate, found);

        // Devolver el producto actualizado envuelto en un Optional
        return Optional.of(found);
	}
	
//
//    public void deleteItemById(Long id) {
//        productoRepository.deleteById(id);
//    }
}
