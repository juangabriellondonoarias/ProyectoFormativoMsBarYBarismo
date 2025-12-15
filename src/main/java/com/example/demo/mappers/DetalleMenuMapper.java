package com.example.demo.mappers;

import com.example.demo.dto.DetalleMenuRequestDTO;
import com.example.demo.dto.DetalleMenuResponseDTO;
import com.example.demo.models.DetalleMenu;
import com.example.demo.models.Menu;
import org.springframework.stereotype.Component;

@Component
public class DetalleMenuMapper {

    // ===== ENTITY -> RESPONSE DTO =====
    public DetalleMenuResponseDTO toResponseDTO(DetalleMenu entity) {
        DetalleMenuResponseDTO dto = new DetalleMenuResponseDTO();

        dto.setId(entity.getId());

        if (entity.getMenu() != null) {
            dto.setMenuId(entity.getMenu().getId());
        }

        return dto;
    }

    // ===== REQUEST DTO -> ENTITY =====
    public DetalleMenu toEntity(DetalleMenuRequestDTO dto) {
        DetalleMenu entity = new DetalleMenu();

        Menu menu = new Menu();
        menu.setId(dto.getMenuId());
        entity.setMenu(menu);

        return entity;
    }
}
