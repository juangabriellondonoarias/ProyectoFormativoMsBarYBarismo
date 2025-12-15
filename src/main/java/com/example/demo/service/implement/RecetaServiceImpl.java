package com.example.demo.service.implement;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.mappers.RecetaMapper;
import com.example.demo.models.Ingrediente;
import com.example.demo.models.Receta;
import com.example.demo.models.RecetaIngrediente;
import com.example.demo.repository.IngredienteRepository; // <--- IMPORTANTE
import com.example.demo.repository.RecetaRepository;
import com.example.demo.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository repositorio;

    @Autowired // <--- TE FALTA ESTA INYECCIÓN
    private IngredienteRepository ingredienteRepository;

    @Override
    @Transactional
    public RecetaDTO guardar(RecetaDTO dto) {
        // Validaciones básicas de FK
        if(dto.getIdCategoria() == null) {
            throw new RuntimeException("La receta debe tener una Categoría asignada.");
        }
        
        // 1. Convertimos el DTO a Entidad (Aquí los ingredientes están "desconectados")
        Receta receta = RecetaMapper.toEntity(dto);

        // 2. BUSCAMOS LOS INGREDIENTES REALES (ESTO ES LO QUE TE FALTA)
        if (receta.getIngredientes() != null) {
            for (RecetaIngrediente ri : receta.getIngredientes()) {
                // Obtenemos el ID que viene del JSON
                Integer idIngrediente = ri.getIngrediente().getIdIngrediente();
                
                // Buscamos el objeto REAL en la base de datos
                Ingrediente ingredienteReal = ingredienteRepository.findById(idIngrediente)
                        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado con ID: " + idIngrediente));
                
                // Reemplazamos el objeto "falso" por el "real" para que Hibernate no falle
                ri.setIngrediente(ingredienteReal);
                
                // Aseguramos la relación bidireccional
                ri.setReceta(receta);
            }
        }

        // 3. Ahora sí guardamos sin errores
        return RecetaMapper.toDTO(repositorio.save(receta));
    }

    // --- EL RESTO DE MÉTODOS LOS PUEDES DEJAR IGUAL ---

    @Transactional
    public RecetaDTO guardarCompleta(RecetaDTO dto) {
        dto.setIdReceta(null);
        Receta recetaEntity = RecetaMapper.toEntity(dto);
        // Aplica la misma lógica de ingredientes aquí si usas este método
        return RecetaMapper.toDTO(repositorio.save(recetaEntity));
    }

    @Override
    public List<RecetaDTO> listarTodas() {
        return repositorio.findAll().stream()
                .map(RecetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDTO obtenerPorId(Integer id) {
        Receta receta = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
        return RecetaMapper.toDTO(receta);
    }

    @Override
    public RecetaDTO actualizar(Integer id, RecetaDTO dto) {
        Receta recetaExistente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));

        recetaExistente.setNombreReceta(dto.getNombreReceta());
        recetaExistente.setTiempoPreparacion(dto.getTiempoPreparacion());
        recetaExistente.setPorciones(dto.getPorciones());
        recetaExistente.setTemperatura(dto.getTemperatura());
        recetaExistente.setNotasAdicionales(dto.getNotasAdicionales());

        Receta temp = RecetaMapper.toEntity(dto);
        recetaExistente.setCategoria(temp.getCategoria());

        return RecetaMapper.toDTO(repositorio.save(recetaExistente));
    }
    
    @Override
    public RecetaDTO actualizarParcial(Integer id, RecetaDTO dto) {
        Receta receta = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));

        if (dto.getNombreReceta() != null) receta.setNombreReceta(dto.getNombreReceta());
        if (dto.getTiempoPreparacion() != null) receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        if (dto.getPorciones() != null) receta.setPorciones(dto.getPorciones());
        if (dto.getTemperatura() != null) receta.setTemperatura(dto.getTemperatura());
        if (dto.getNotasAdicionales() != null) receta.setNotasAdicionales(dto.getNotasAdicionales());

        Receta actualizada = repositorio.save(receta);
        return RecetaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminar(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con ID: " + id);
        }
        repositorio.deleteById(id);
    }
