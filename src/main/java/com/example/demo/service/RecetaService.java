package com.example.demo.service;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.models.Receta;
import com.example.demo.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecetaService {
    
    private final RecetaRepository recetaRepository;
    
    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository; // OBLIGATORIO
    }
    
    /**
     * Obtener todas las recetas
     */
    @Transactional(readOnly = true)
    public List<RecetaDTO> obtenerTodas() {
        return recetaRepository.findAllWithCategoryAndModule()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener receta por ID
     */
    @Transactional(readOnly = true)
    public RecetaDTO obtenerPorId(Integer id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con ID: " + id));
        return convertirADTO(receta);
    }
    
    /**
     * Obtener receta con detalles completos (ingredientes y pasos)
     */
    @Transactional(readOnly = true)
    public RecetaDTO obtenerConDetalles(Integer id) {
        Receta receta = recetaRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con ID: " + id));
        return convertirADTOCompleto(receta);
    }
    
    /**
     * Crear nueva receta
     */
    @Transactional
    public RecetaDTO crear(RecetaDTO recetaDTO) {
        // Validar nombre único
        if (recetaRepository.existsByNombreReceta(recetaDTO.getNombreReceta())) {
            throw new DuplicateResourceException("Ya existe una receta con el nombre: " + recetaDTO.getNombreReceta());
        }
        
        Receta receta = convertirAEntidad(recetaDTO);
        Receta recetaGuardada = recetaRepository.save(receta);
        return convertirADTO(recetaGuardada);
    }
    
    /**
     * Actualizar receta existente
     */
    @Transactional
    public RecetaDTO actualizar(Integer id, RecetaDTO recetaDTO) {
        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con ID: " + id));
        
        // Validar nombre único (excepto la misma receta)
        if (!recetaExistente.getNombreReceta().equals(recetaDTO.getNombreReceta()) &&
            recetaRepository.existsByNombreReceta(recetaDTO.getNombreReceta())) {
            throw new DuplicateResourceException("Ya existe una receta con el nombre: " + recetaDTO.getNombreReceta());
        }
        
        // Actualizar campos
        recetaExistente.setIdCategoria(recetaDTO.getIdCategoria());
        recetaExistente.setIdModulo(recetaDTO.getIdModulo());
        recetaExistente.setNombreReceta(recetaDTO.getNombreReceta());
        recetaExistente.setTiempoPreparacion(recetaDTO.getTiempoPreparacion());
        recetaExistente.setPorciones(recetaDTO.getPorciones());
        recetaExistente.setTemperatura(recetaDTO.getTemperatura());
        recetaExistente.setNotasAdicionales(recetaDTO.getNotasAdicionales());
        
        Receta recetaActualizada = recetaRepository.save(recetaExistente);
        return convertirADTO(recetaActualizada);
    }
    
    /**
     * Eliminar receta
     */
    @Transactional
    public void eliminar(Integer id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta no encontrada con ID: " + id);
        }
        recetaRepository.deleteById(id);
    }
    
    /**
     * Buscar recetas por categoría
     */
    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarPorCategoria(Integer idCategoria) {
        return recetaRepository.findByIdCategoria(idCategoria)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Buscar recetas por módulo
     */
    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarPorModulo(Integer idModulo) {
        return recetaRepository.findByIdModulo(idModulo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Buscar recetas por nombre (parcial)
     */
    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarPorNombre(String nombre) {
        return recetaRepository.buscarPorNombreParcial(nombre)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Buscar recetas por tiempo de preparación máximo
     */
    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarPorTiempoMaximo(Integer tiempoMaximo) {
        return recetaRepository.findByTiempoPreparacionLessThanEqual(tiempoMaximo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    // ========== MÉTODOS DE CONVERSIÓN ==========
    
    /**
     * Convertir entidad Receta a DTO (básico)
     */
    private RecetaDTO convertirADTO(Receta receta) {
        RecetaDTO dto = new RecetaDTO();
        dto.setIdReceta(receta.getIdReceta());
        dto.setIdCategoria(receta.getIdCategoria());
        dto.setIdModulo(receta.getIdModulo());
        dto.setNombreReceta(receta.getNombreReceta());
        dto.setTiempoPreparacion(receta.getTiempoPreparacion());
        dto.setPorciones(receta.getPorciones());
        dto.setTemperatura(receta.getTemperatura());
        dto.setNotasAdicionales(receta.getNotasAdicionales());
        
        // Información adicional si está disponible
        if (receta.getCategoria() != null) {
            dto.setNombreCategoria(receta.getCategoria().getNombreCategoria());
        }
        return dto;
    }
    
    /**
     * Convertir entidad Receta a DTO con detalles completos
     */
    private RecetaDTO convertirADTOCompleto(Receta receta) {
        RecetaDTO dto = convertirADTO(receta);
        
        // Agregar ingredientes si existen
        if (receta.getIngredientes() != null && !receta.getIngredientes().isEmpty()) {
            // TODO: Implementar mapeo de ingredientes cuando sea necesario
        }
        
        // Agregar pasos si existen
        if (receta.getPasos() != null && !receta.getPasos().isEmpty()) {
            // TODO: Implementar mapeo de pasos cuando sea necesario
        }
        
        return dto;
    }
    
    /**
     * Convertir DTO a entidad Receta
     */
    private Receta convertirAEntidad(RecetaDTO dto) {
        Receta receta = new Receta();
        receta.setIdCategoria(dto.getIdCategoria());
        receta.setIdModulo(dto.getIdModulo());
        receta.setNombreReceta(dto.getNombreReceta());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setPorciones(dto.getPorciones() != null ? dto.getPorciones() : 1);
        receta.setTemperatura(dto.getTemperatura());
        receta.setNotasAdicionales(dto.getNotasAdicionales());
        return receta;
    }
}