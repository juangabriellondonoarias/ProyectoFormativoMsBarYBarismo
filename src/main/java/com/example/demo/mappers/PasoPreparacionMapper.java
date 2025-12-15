package com.example.demo.mappers;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.models.PasoPreparacion;
import org.springframework.stereotype.Component;

@Component
public class PasoPreparacionMapper {

    // ===== ENTITY -> DTO =====
    public PasoPreparacionDTO toDTO(PasoPreparacion entity) {
        PasoPreparacionDTO dto = new PasoPreparacionDTO();

        dto.setidPaso(entity.getIdPaso());
        dto.setIdReceta(entity.getIdReceta());
        dto.setOrden(entity.getOrden());
        dto.setDescripcionPaso(entity.getDescripcionPaso());

        if (entity.getReceta() != null) {
            dto.setnombreReceta(entity.getReceta().getNombreReceta());
        }

        return dto;
    }

    // ===== DTO -> ENTITY =====
    public PasoPreparacion toEntity(PasoPreparacionDTO dto) {
        PasoPreparacion entity = new PasoPreparacion();

        entity.setIdPaso(dto.getidPaso());
        entity.setIdReceta(dto.getIdReceta());
        entity.setOrden(dto.getOrden());
        entity.setDescripcionPaso(dto.getDescripcionPaso());

        return entity;
    }
}
