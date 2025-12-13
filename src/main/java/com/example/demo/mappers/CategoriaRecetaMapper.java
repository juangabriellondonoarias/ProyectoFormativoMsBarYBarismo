package com.example.demo.mappers;

import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.models.CategoriaReceta;
import org.springframework.stereotype.Component;

@Component
public class CategoriaRecetaMapper {

    // ===== ENTITY -> DTO =====
    public CategoriaRecetaDTO toDTO(CategoriaReceta entity) {
        CategoriaRecetaDTO dto = new CategoriaRecetaDTO();
        dto.setIdCategoria(entity.getIdCategoria());
        dto.setNombreCategoria(entity.getNombreCategoria());
        return dto;
    }

    // ===== DTO -> ENTITY =====
    public CategoriaReceta toEntity(CategoriaRecetaDTO dto) {
        CategoriaReceta entity = new CategoriaReceta();
        entity.setIdCategoria(dto.getIdCategoria());
        entity.setNombreCategoria(dto.getNombreCategoria());
        return entity;
    }
}
