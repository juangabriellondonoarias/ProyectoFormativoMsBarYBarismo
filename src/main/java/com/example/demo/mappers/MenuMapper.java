package com.example.demo.mappers;

import com.example.demo.dto.MenuRequestDTO;
import com.example.demo.models.Menu;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MenuMapper {

    // ===== REQUEST DTO -> ENTITY =====
    public Menu toEntity(MenuRequestDTO dto) {
        Menu entity = new Menu();

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        entity.setActivo(true);

        return entity;
    }
}
