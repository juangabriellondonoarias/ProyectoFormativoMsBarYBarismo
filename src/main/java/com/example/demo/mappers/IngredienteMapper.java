package com.example.demo.mappers;

import com.example.demo.dto.IngredienteDTO;
import com.example.demo.models.Ingrediente;
import org.springframework.stereotype.Component;

@Component
public class IngredienteMapper {

    // ===== ENTITY -> DTO =====
    public IngredienteDTO toDTO(Ingrediente entity) {
        IngredienteDTO dto = new IngredienteDTO();

        dto.setIdIngrediente(entity.getIdIngrediente());
        dto.setNombre(entity.getNombre());
        dto.setCantidad(entity.getCantidad());
        dto.setUnidadMedidaStock(entity.getUnidadMedidaStock());

        return dto;
    }

    // ===== DTO -> ENTITY =====
    public Ingrediente toEntity(IngredienteDTO dto) {
        Ingrediente entity = new Ingrediente();

        entity.setIdIngrediente(dto.getIdIngrediente());
        entity.setNombre(dto.getNombre());
        entity.setCantidad(dto.getCantidad());
        entity.setUnidadMedidaStock(dto.getUnidadMedidaStock());

        return entity;
    }
}
