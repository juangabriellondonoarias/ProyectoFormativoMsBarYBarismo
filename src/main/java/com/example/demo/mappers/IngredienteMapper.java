package com.example.demo.mappers;
import com.example.demo.dto.IngredienteDTO;
import com.example.demo.models.Ingrediente;

public class IngredienteMapper {
	  public static IngredienteDTO toDTO(Ingrediente ingrediente) {
	        IngredienteDTO dto = new IngredienteDTO();
	        dto.setIdIngrediente(ingrediente.getIdIngrediente());
	        dto.setNombre(ingrediente.getNombre());
	        dto.setCantidad(ingrediente.getCantidad());
	        dto.setUnidadMedidaStock(ingrediente.getUnidadMedidaStock());
	        return dto;
	    }

	    public static Ingrediente toEntity(IngredienteDTO dto) {
	        Ingrediente ingrediente = new Ingrediente();
	        ingrediente.setIdIngrediente(dto.getIdIngrediente());
	        ingrediente.setNombre(dto.getNombre());
	        ingrediente.setCantidad(dto.getCantidad());
	        ingrediente.setUnidadMedidaStock(dto.getUnidadMedidaStock());
	        return ingrediente;
	    }
}