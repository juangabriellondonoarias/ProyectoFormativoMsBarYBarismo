package com.example.demo.service;
import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.models.CategoriaReceta;
import com.example.demo.repository.CategoriaRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaRecetaService {

	@Autowired
    private CategoriaRecetaRepository repository;

    // 1. LISTAR TODAS
    public List<CategoriaRecetaDTO> obtenerTodas() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // 2. OBTENER POR ID
    public CategoriaRecetaDTO obtenerPorId(Integer id) {
        CategoriaReceta categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return convertirADTO(categoria);
    }

    // 3. CREAR
    public CategoriaRecetaDTO crear(CategoriaRecetaDTO dto) {
        if (repository.existsByNombreCategoria(dto.getNombreCategoria())) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + dto.getNombreCategoria());
        }
        CategoriaReceta entidad = convertirAEntidad(dto);
        CategoriaReceta guardado = repository.save(entidad);
        return convertirADTO(guardado);
    }

    // 4. ACTUALIZAR
    public CategoriaRecetaDTO actualizar(Integer id, CategoriaRecetaDTO dto) {
        CategoriaReceta existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Validar si cambio el nombre, que no choque con otra existente
        if (!existente.getNombreCategoria().equals(dto.getNombreCategoria()) 
            && repository.existsByNombreCategoria(dto.getNombreCategoria())) {
            throw new RuntimeException("Ya existe otra categoría con ese nombre");
        }

        existente.setNombreCategoria(dto.getNombreCategoria());
        
        CategoriaReceta actualizado = repository.save(existente);
        return convertirADTO(actualizado);
    }

    // 5. ELIMINAR
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, ID no existe");
        }
        repository.deleteById(id);
    }

    // --- MÉTODOS AUXILIARES (Mappers) ---
    private CategoriaRecetaDTO convertirADTO(CategoriaReceta entidad) {
        CategoriaRecetaDTO dto = new CategoriaRecetaDTO();
        dto.setIdCategoria(entidad.getIdCategoria());
        dto.setNombreCategoria(entidad.getNombreCategoria());
        return dto;
    }

    private CategoriaReceta convertirAEntidad(CategoriaRecetaDTO dto) {
        CategoriaReceta entidad = new CategoriaReceta();
        entidad.setNombreCategoria(dto.getNombreCategoria());
        return entidad;
    }
	
}
