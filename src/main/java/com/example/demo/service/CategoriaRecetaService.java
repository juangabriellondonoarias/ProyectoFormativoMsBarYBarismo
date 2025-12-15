package com.example.demo.service;
import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.models.CategoriaReceta;
import com.example.demo.repository.CategoriaRecetaRepository;
import com.example.demo.service.CategoriaRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaRecetaService {
	
	@Autowired
	private CategoriaRecetaRepository repositorio;
	
	private CategoriaRecetaDTO mapearADTO(CategoriaReceta categoria) {
	    CategoriaRecetaDTO dto = new CategoriaRecetaDTO();
	    dto.setIdCategoria(categoria.getIdCategoria());
	    dto.setNombreCategoria(categoria.getNombreCategoria());
	    return dto;
	}

	private CategoriaReceta mapearAEntidad(CategoriaRecetaDTO dto) {
	    CategoriaReceta categoria = new CategoriaReceta();
	    // No seteo el ID porque es autoincremental al guardar
	    categoria.setNombreCategoria(dto.getNombreCategoria());
	    return categoria;
	}

    @Override
    public List<CategoriaRecetaDTO> listarTodas() {
        return repositorio.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaRecetaDTO obtenerPorId(Integer id) {
        CategoriaReceta categoria = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría de receta no encontrada con ID: " + id));
        return mapearADTO(categoria);
    }

    @Override
    public CategoriaRecetaDTO guardar(CategoriaRecetaDTO categoriaDTO) {
        CategoriaReceta categoria = mapearAEntidad(categoriaDTO);
        CategoriaReceta nuevaCategoria = repositorio.save(categoria);
        return mapearADTO(nuevaCategoria);
    }

    @Override
    public CategoriaRecetaDTO actualizar(Integer id, CategoriaRecetaDTO categoriaDTO) {
        CategoriaReceta categoriaExistente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría de receta no encontrada con ID: " + id));
        
        categoriaExistente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        
        CategoriaReceta actualizada = repositorio.save(categoriaExistente);
        return mapearADTO(actualizada);
    }

    @Override
    public void eliminar(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Categoría de receta no encontrada con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}