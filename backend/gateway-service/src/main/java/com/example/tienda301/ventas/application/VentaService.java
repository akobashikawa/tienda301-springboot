package com.example.tienda301.ventas.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.tienda301.personas.application.PersonaService;
import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.productos.application.ProductoService;
import com.example.tienda301.productos.domain.Producto;
import com.example.tienda301.ventas.domain.Venta;
import com.example.tienda301.ventas.domain.VentaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

//	private final VentaRepository ventaRepository;
	private final RestTemplate restTemplate;

	private final ProductoService productoService;
    private final PersonaService personaService;
    
    public VentaService(RestTemplate restTemplate, ProductoService productoService, PersonaService personaService) {
    	this.restTemplate = restTemplate;
        this.productoService = productoService;
        this.personaService = personaService;
    }
    

	// URL del microservicio de productos
	private final String ventaServiceUrl = "http://localhost:8083/api/ventas";

    public List<Venta> getItems() {
    	Venta[] ventas = restTemplate.getForObject(ventaServiceUrl, Venta[].class);
		return Arrays.asList(ventas);
	}

    public Optional<Venta> getItemById(Long id) {
		String url = ventaServiceUrl + "/" + id;
		Venta venta = restTemplate.getForObject(url, Venta.class);
		return Optional.ofNullable(venta);
	}
    
    public Venta createItem(Venta venta) {
		return restTemplate.postForObject(ventaServiceUrl, venta, Venta.class);
	}

    public Optional<Venta> updateItem(Long id, Venta venta) {
        String url = ventaServiceUrl + "/" + id;
        restTemplate.put(url, venta);
        return Optional.of(getItemById(id).orElse(null));  // Retornar el item actualizado
    }
    
    public void deleteItemById(Long id) {
    	String url = ventaServiceUrl + "/" + id;
        restTemplate.delete(url);
    }
    
}
