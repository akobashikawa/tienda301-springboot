package com.example.tienda301.personas.infrastructure;

import org.springframework.stereotype.Repository;

import com.example.tienda301.personas.domain.Persona;
import com.example.tienda301.personas.domain.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaJpaRepository implements PersonaRepository {

    private final JpaPersonaRepository jpaPersonaRepository;

    public PersonaJpaRepository(JpaPersonaRepository jpaPersonaRepository) {
        this.jpaPersonaRepository = jpaPersonaRepository;
    }

    @Override
    public List<Persona> findAll() {
        return jpaPersonaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return jpaPersonaRepository.findById(id);
    }

    @Override
    public Persona save(Persona producto) {
        return jpaPersonaRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        jpaPersonaRepository.deleteById(id);
    }
}
