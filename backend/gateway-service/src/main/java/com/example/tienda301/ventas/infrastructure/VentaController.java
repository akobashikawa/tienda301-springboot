package com.example.tienda301.ventas.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.productos.domain.Producto;
import com.example.tienda301.ventas.application.VentaDTO;
import com.example.tienda301.ventas.application.VentaResponseDTO;
import com.example.tienda301.ventas.application.VentaService;
import com.example.tienda301.ventas.domain.Venta;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaResponseDTO> getItems() {
        return ventaService.getItems().stream().map(this::mapToVentaResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<VentaResponseDTO> getItemById(@PathVariable Long id) {
    	Venta venta = ventaService.getItemById(id).get();
        return Optional.ofNullable(mapToVentaResponseDTO(venta));
    }

    @PostMapping
    public Venta createItem(@RequestBody VentaDTO ventaDTO) {
    	Venta venta = new Venta();
    	Producto producto = new Producto();
    	producto.setId(ventaDTO.getProducto_id());
    	Persona persona = new Persona();
    	persona.setId(ventaDTO.getPersona_id());
        venta.setProducto(producto);
        venta.setPersona(persona);
        venta.setPrecio(ventaDTO.getPrecio());
        venta.setCantidad(ventaDTO.getCantidad());
        return ventaService.createItem(venta);
    }

    @PutMapping("/{id}")
    public Optional<Venta> updateItem(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
    	Venta venta = new Venta();
    	Producto producto = new Producto();
    	producto.setId(ventaDTO.getProducto_id());
    	Persona persona = new Persona();
    	persona.setId(ventaDTO.getPersona_id());
        venta.setProducto(producto);
        venta.setPersona(persona);
        venta.setPrecio(ventaDTO.getPrecio());
        venta.setCantidad(ventaDTO.getCantidad());
        return ventaService.updateItem(id, venta);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        ventaService.deleteItemById(id);
    }
    
    private VentaResponseDTO mapToVentaResponseDTO(Venta venta) {
        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setId(venta.getId());
        dto.setProducto(venta.getProducto());
        dto.setProducto_id(venta.getProducto().getId());
        dto.setPersona(venta.getPersona());
        dto.setPersona_id(venta.getPersona().getId());
        dto.setPrecio(venta.getPrecio());
        dto.setCantidad(venta.getCantidad());
        dto.setFecha(venta.getFecha());
        return dto;
    }
}
