package com.example.tienda301.personas.domain;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
	List<Persona> findAll();

	Optional<Persona> findById(Long id);

	Persona save(Persona producto);

	void deleteById(Long id);
}
