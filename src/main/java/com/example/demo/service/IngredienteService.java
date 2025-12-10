package com.example.demo.service;

import com.example.demo.dto.IngredienteDTO;
import com.example.demo.models.Ingrediente;
import com.example.demo.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

	@Autowired
    private IngredienteRepository repository;

    // 1. LISTAR TODOS
    public List<IngredienteDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // 2. OBTENER POR ID
    public IngredienteDTO obtenerPorId(Integer id) {
        Ingrediente ingrediente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado con ID: " + id));
        return convertirADTO(ingrediente);
    }

    // 3. CREAR
    public IngredienteDTO crear(IngredienteDTO dto) {
        if (repository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe un ingrediente con el nombre: " + dto.getNombre());
        }
        Ingrediente entidad = convertirAEntidad(dto);
        Ingrediente guardado = repository.save(entidad);
        return convertirADTO(guardado);
    }

    // 4. ACTUALIZAR
    public IngredienteDTO actualizar(Integer id, IngredienteDTO dto) {
        Ingrediente existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setUnidadMedidaStock(dto.getUnidadMedidaStock());
        
        Ingrediente actualizado = repository.save(existente);
        return convertirADTO(actualizado);
    }

    // 5. ELIMINAR
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, ID no existe");
        }
        repository.deleteById(id);
    }

    // --- MÉTODOS AUXILIARES (Mappers manuales) ---
    private IngredienteDTO convertirADTO(Ingrediente entidad) {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setIdIngrediente(entidad.getIdIngrediente());
        dto.setNombre(entidad.getNombre());
        dto.setUnidadMedidaStock(entidad.getUnidadMedidaStock());
        return dto;
    }

    private Ingrediente convertirAEntidad(IngredienteDTO dto) {
        Ingrediente entidad = new Ingrediente();
        // No seteamos ID al crear, lo genera la BD
        entidad.setNombre(dto.getNombre());
        entidad.setUnidadMedidaStock(dto.getUnidadMedidaStock());
        return entidad;
    }
}
