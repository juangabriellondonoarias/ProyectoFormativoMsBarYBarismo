package com.example.demo.service.implement;

import com.example.demo.dto.IngredienteDTO;
import com.example.demo.models.Ingrediente;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    private IngredienteRepository repositorio;

    @Override
    public List<IngredienteDTO> listarTodos() {
        return repositorio.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public IngredienteDTO obtenerPorId(Integer id) {
        Ingrediente ingrediente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        return mapearADTO(ingrediente);
    }

    @Override
    public IngredienteDTO guardar(IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = mapearAEntidad(ingredienteDTO);
        Ingrediente nuevoIngrediente = repositorio.save(ingrediente);
        return mapearADTO(nuevoIngrediente);
    }

    @Override
    public IngredienteDTO actualizar(Integer id, IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        
        ingrediente.setNombre(ingredienteDTO.getNombre());
        ingrediente.setUnidadMedidaStock(ingredienteDTO.getUnidadMedidaStock());
        
        Ingrediente actualizado = repositorio.save(ingrediente);
        return mapearADTO(actualizado);
    }
    
    @Override
    public IngredienteDTO actualizarStock(Integer id, int nuevaCantidad) {
        Ingrediente ingrediente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        
        // Solo actualizamos la cantidad, lo demás queda igual
        ingrediente.setCantidad(nuevaCantidad);
        
        Ingrediente guardado = repositorio.save(ingrediente);
        return mapearADTO(guardado);
    }

    @Override
    public void eliminar(Integer id) {
        Ingrediente ingrediente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        repositorio.delete(ingrediente);
    }

    
    private IngredienteDTO mapearADTO(Ingrediente ingrediente) {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setIdIngrediente(ingrediente.getIdIngrediente());
        dto.setNombre(ingrediente.getNombre());
        dto.setCantidad(ingrediente.getCantidad());
        dto.setUnidadMedidaStock(ingrediente.getUnidadMedidaStock());
        return dto;
    }

    private Ingrediente mapearAEntidad(IngredienteDTO dto) {
        Ingrediente ingrediente = new Ingrediente();
        // No seteo ID porque es autoincremental al guardar
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setCantidad(dto.getCantidad());
        ingrediente.setUnidadMedidaStock(dto.getUnidadMedidaStock());
        return ingrediente;
    }
}
