package com.example.demo.repository;

import com.example.demo.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    // Método extra útil: Verificar si ya existe un ingrediente con ese nombre
    boolean existsByNombre(String nombre);
}
