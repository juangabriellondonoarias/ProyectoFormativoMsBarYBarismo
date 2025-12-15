package com.example.demo.mappers;
import com.example.demo.dto.CategoriaRecetaDTO;
import com.example.demo.models.CategoriaReceta;

public class CategoriaRecetaMapper {
	// Convierte de Entidad (Base de Datos) a DTO (Para el Cliente)
    public static CategoriaRecetaDTO toDTO(CategoriaReceta categoria) {
        if (categoria == null) {
            return null;
        }
        
        CategoriaRecetaDTO dto = new CategoriaRecetaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombreCategoria(categoria.getNombreCategoria());
        
        return dto;
    }

    // Convierte de DTO (Desde el Cliente) a Entidad (Para Base de Datos)
    public static CategoriaReceta toEntity(CategoriaRecetaDTO dto) {
        if (dto == null) {
            return null;
        }
        
        CategoriaReceta categoria = new CategoriaReceta();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombreCategoria(dto.getNombreCategoria());
        
        return categoria;
    }
}