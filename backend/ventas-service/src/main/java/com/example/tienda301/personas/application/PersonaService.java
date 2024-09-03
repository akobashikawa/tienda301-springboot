package com.example.tienda301.personas.application;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.personas.domain.PersonaRepository;
import com.example.tienda301.productos.domain.Producto;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

//    private final PersonaRepository personaRepository;
    private final RestTemplate restTemplate;
    
    private final String personaServiceUrl = "http://localhost:8082/api/personas"; // URL del microservicio de personas

//    public PersonaService(PersonaRepository personaRepository) {
//        this.personaRepository = personaRepository;
//    }
    
    public PersonaService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

//    public List<Persona> getItems() {
//        return personaRepository.findAll();
//    }

//    public Optional<Persona> getItemById(Long id) {
//        return personaRepository.findById(id);
//    }
    
    public Optional<Persona> getItemById(Long id) {
		String url = personaServiceUrl + id;
		try {
			Persona persona = restTemplate.getForObject(url, Persona.class);
			return Optional.ofNullable(persona); // Envuelve el resultado en un Optional
		} catch (Exception e) {
			return Optional.empty(); // En caso de error o si no se encuentra el item, devuelve Optional.empty()
		}
	}

//    public Persona createItem(Persona persona) {
//        return personaRepository.save(persona);
//    }
    
//    public Persona updateItem(Long id, Persona persona) {
//    	persona.setId(id);
//    	return personaRepository.save(persona);
//    }
    
    public Optional<Persona> updateItem(Long id, Persona persona) {
        String urlGet = personaServiceUrl + id;
        String urlUpdate = personaServiceUrl + id;

        // Obtener la persona existente
        Persona found = restTemplate.getForObject(urlGet, Persona.class);
        if (found == null) {
            return Optional.empty();
        }

        // Actualizar los campos necesarios
        if (persona.getNombre() != null) {
            found.setNombre(persona.getNombre());
        }

        // Enviar la actualización al microservicio de personas
        restTemplate.put(urlUpdate, found);

        // Devolver la persona actualizada envuelta en un Optional
        return Optional.of(found);
    }

//    public void deleteItemById(Long id) {
//        personaRepository.deleteById(id);
//    }
}
