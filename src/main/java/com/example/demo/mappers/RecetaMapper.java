package com.example.demo.mappers;

import com.example.demo.dto.RecetaDTO;
import com.example.demo.models.Receta;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapper {

    // ===== ENTITY -> DTO =====
    public RecetaDTO toDTO(Receta entity) {
        RecetaDTO dto = new RecetaDTO();

        dto.setIdReceta(entity.getIdReceta());
        dto.setIdCategoria(entity.getIdCategoria());
        dto.setIdModulo(entity.getIdModulo());
        dto.setNombreReceta(entity.getNombreReceta());
        dto.setTiempoPreparacion(entity.getTiempoPreparacion());
        dto.setPorciones(entity.getPorciones());
        dto.setTemperatura(entity.getTemperatura());
        dto.setNotasAdicionales(entity.getNotasAdicionales());

        if (entity.getCategoria() != null) {
            dto.setNombreCategoria(entity.getCategoria().getNombreCategoria());
        }

        return dto;
    }

    // ===== DTO -> ENTITY =====
    public Receta toEntity(RecetaDTO dto) {
        Receta entity = new Receta();

        entity.setIdReceta(dto.getIdReceta());
        entity.setIdCategoria(dto.getIdCategoria());
        entity.setIdModulo(dto.getIdModulo());
        entity.setNombreReceta(dto.getNombreReceta());
        entity.setTiempoPreparacion(dto.getTiempoPreparacion());
        entity.setPorciones(dto.getPorciones());
        entity.setTemperatura(dto.getTemperatura());
        entity.setNotasAdicionales(dto.getNotasAdicionales());

        return entity;
    }
}
