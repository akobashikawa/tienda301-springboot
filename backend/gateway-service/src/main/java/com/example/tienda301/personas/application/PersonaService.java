package com.example.tienda301.personas.application;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.tienda301.personas.domain.Persona;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final RestTemplate restTemplate;

    private final String personaServiceUrl = "http://localhost:8082/api/personas"; // URL del microservicio de personas

    public PersonaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Persona> getItems() {
        Persona[] personas = restTemplate.getForObject(personaServiceUrl, Persona[].class);
        return Arrays.asList(personas);
    }

    public Optional<Persona> getItemById(Long id) {
        String url = personaServiceUrl + "/" + id;
        Persona persona = restTemplate.getForObject(url, Persona.class);
        return Optional.ofNullable(persona);
    }

    public Persona createItem(Persona persona) {
        return restTemplate.postForObject(personaServiceUrl, persona, Persona.class);
    }
    
    public Optional<Persona> updateItem(Long id, Persona persona) {
        String url = personaServiceUrl + "/" + id;
        restTemplate.put(url, persona);
        return Optional.of(getItemById(id).orElse(null));  // Retornar la persona actualizada
    }

    public void deleteItemById(Long id) {
        String url = personaServiceUrl + "/" + id;
        restTemplate.delete(url);
    }
}
