package com.example.demo.mappers;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.models.PasoPreparacion;
import com.example.demo.models.Receta;

public class PasoPreparacionMapper {
	public static PasoPreparacionDTO toDTO(PasoPreparacion paso) {
        if (paso == null) return null;

        PasoPreparacionDTO dto = new PasoPreparacionDTO();
        dto.setIdPaso(paso.getIdPaso());
        dto.setOrden(paso.getOrden());
        dto.setDescripcionPaso(paso.getDescripcionPaso());
        
        // Extraemos el ID de la receta padre si existe
        if (paso.getReceta() != null) {
            dto.setIdReceta(paso.getReceta().getIdReceta());
        }

        return dto;
    }

    public static PasoPreparacion toEntity(PasoPreparacionDTO dto) {
        if (dto == null) return null;

        PasoPreparacion paso = new PasoPreparacion();
        paso.setOrden(dto.getOrden());
        paso.setDescripcionPaso(dto.getDescripcionPaso());

        // Vinculamos con la Receta usando solo el ID
        if (dto.getIdReceta() != null) {
            Receta recetaDummy = new Receta();
            recetaDummy.setIdReceta(dto.getIdReceta());
            paso.setReceta(recetaDummy);
        }

        return paso;
    }
}
