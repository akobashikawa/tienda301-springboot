package com.example.tienda301.personas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tienda301.personas.domain.Persona;

public interface JpaPersonaRepository extends JpaRepository<Persona, Long> {
}
