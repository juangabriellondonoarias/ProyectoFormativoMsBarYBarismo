package com.example.demo.mappers;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.dto.RecetaDTO;
import com.example.demo.dto.RecetaIngredienteDTO;
import com.example.demo.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecetaMapper {

	public static Receta toEntity(RecetaDTO dto) {
		if (dto == null)
			return null;

		Receta receta = new Receta();
		// Mapeo simple de campos
		receta.setIdReceta(dto.getIdReceta());
		receta.setNombreReceta(dto.getNombreReceta());
		receta.setTiempoPreparacion(dto.getTiempoPreparacion());
		receta.setPorciones(dto.getPorciones());
		receta.setTemperatura(dto.getTemperatura());
		receta.setNotasAdicionales(dto.getNotasAdicionales());

		if (dto.getIdCategoria() != null) {
			CategoriaReceta cat = new CategoriaReceta();
			cat.setIdCategoria(dto.getIdCategoria());
			receta.setCategoria(cat);
		}

		// 1. Mapear Pasos
		if (dto.getPasos() != null) {
			List<PasoPreparacion> pasosEntities = dto.getPasos().stream().map(pasoDTO -> {
				PasoPreparacion paso = PasoPreparacionMapper.toEntity(pasoDTO);
				// VITAL: Vincular el hijo con el padre antes de guardar
				paso.setReceta(receta);
				return paso;
			}).collect(Collectors.toList());
			receta.setPasos(pasosEntities);
		}

		// 2. Mapear Ingredientes
		if (dto.getIngrediente() != null) {
			List<RecetaIngrediente> ingredientes = new ArrayList<>();
			for (RecetaIngredienteDTO ingDTO : dto.getIngrediente()) {
				RecetaIngrediente ri = new RecetaIngrediente();

				// Configurar relaciones
				ri.setReceta(receta);

				Ingrediente ingredienteStock = new Ingrediente();
				ingredienteStock.setIdIngrediente(ingDTO.getIdIngrediente());
				ri.setIngrediente(ingredienteStock);

				// --- AGREGA ESTAS LÍNEAS (CORRECCIÓN) ---
				// Inicializamos la clave si es nula (aunque debería estarlo por el constructor)
				if (ri.getId() == null) {
					ri.setId(new RecetaIngredientePK());
				}
				// Asignamos manualmente el ID del ingrediente a la clave compuesta
				ri.getId().setIdIngrediente(ingDTO.getIdIngrediente());
				// ----------------------------------------

				ri.setCantidadRequerida(ingDTO.getCantidadRequerida());
				ri.setUnidadMedida(ingDTO.getUnidadMedida());
				ingredientes.add(ri);
			}
			receta.setIngredientes(ingredientes);
		}
		return receta;
	}

	public static RecetaDTO toDTO(Receta entity) {
		if (entity == null)
			return null;

		RecetaDTO dto = new RecetaDTO();

		dto.setIdReceta(entity.getIdReceta());
		dto.setNombreReceta(entity.getNombreReceta());
		dto.setTiempoPreparacion(entity.getTiempoPreparacion());
		dto.setPorciones(entity.getPorciones());
		dto.setTemperatura(entity.getTemperatura());
		dto.setNotasAdicionales(entity.getNotasAdicionales());

		// ---- Categoria ----
		if (entity.getCategoria() != null) {
			dto.setIdCategoria(entity.getCategoria().getIdCategoria());
			dto.setNombreCategoria(entity.getCategoria().getNombreCategoria());
		}

		// ---- Pasos ----
		if (entity.getPasos() != null) {
			List<PasoPreparacionDTO> pasosDTO = entity.getPasos().stream().map(PasoPreparacionMapper::toDTO)
					.collect(Collectors.toList());
			dto.setPasos(pasosDTO);
		}

		// ---- Ingredientes ----
		if (entity.getIngredientes() != null) {
			List<RecetaIngredienteDTO> ingredientesDTO = entity.getIngredientes().stream().map(ri -> {
				RecetaIngredienteDTO ingDTO = new RecetaIngredienteDTO();
				ingDTO.setIdIngrediente(ri.getIngrediente() != null ? ri.getIngrediente().getIdIngrediente() : null);
				ingDTO.setCantidadRequerida(ri.getCantidadRequerida());
				ingDTO.setUnidadMedida(ri.getUnidadMedida());

				if (ri.getIngrediente() != null) {
					ingDTO.setNombreIngrediente(ri.getIngrediente().getNombre());
				}
				return ingDTO;
			}).collect(Collectors.toList());

			dto.setIngrediente(ingredientesDTO);
		}

		return dto;
	}
}
