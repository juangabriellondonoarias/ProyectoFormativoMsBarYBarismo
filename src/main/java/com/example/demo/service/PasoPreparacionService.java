package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.models.PasoPreparacion;
import com.example.demo.repository.PasoPreparacionRepository;
import com.example.demo.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasoPreparacionService {

	private final PasoPreparacionRepository pasoRepository;
	private final RecetaRepository recetaRepository;

	@Autowired
    public PasoPreparacionService(PasoPreparacionRepository pasoRepository,
                                  RecetaRepository recetaRepository) {
        this.pasoRepository = pasoRepository;
        this.recetaRepository = recetaRepository;
    }
	
	/**
	 * Obtener todos los pasos de preparación
	 */
	@Transactional(readOnly = true)
	public List<PasoPreparacionDTO> obtenerTodos() {
		return pasoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
	}

	/**
	 * Obtener un paso por ID
	 */
	@Transactional(readOnly = true)
	public PasoPreparacionDTO obtenerPorId(Integer id) {
		PasoPreparacion paso = pasoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paso de preparación no encontrado con ID: " + id));
		return convertirADTO(paso);
	}

	/**
	 * Obtener todos los pasos de una receta ordenados
	 */
	@Transactional(readOnly = true)
	public List<PasoPreparacionDTO> obtenerPasosPorReceta(Integer idReceta) {
		// Validar que la receta exista
		if (!recetaRepository.existsById(idReceta)) {
			throw new ResourceNotFoundException("Receta no encontrada con ID: " + idReceta);
		}

		return pasoRepository.findByIdRecetaOrderByOrdenAsc(idReceta).stream().map(this::convertirADTO)
				.collect(Collectors.toList());
	}

	/**
	 * Crear un nuevo paso de preparación
	 */
	@Transactional
	public PasoPreparacionDTO crear(PasoPreparacionDTO pasoDTO) {
		// Validar que la receta exista
		if (!recetaRepository.existsById(pasoDTO.getIdReceta())) {
			throw new ResourceNotFoundException("Receta no encontrada con ID: " + pasoDTO.getIdReceta());
		}

		// Validar que no exista otro paso con el mismo orden en la receta
		if (pasoRepository.existsByIdRecetaAndOrden(pasoDTO.getIdReceta(), pasoDTO.getOrden())) {
			throw new DuplicateResourceException(
					"Ya existe un paso con el orden " + pasoDTO.getOrden() + " en esta receta");
		}

		PasoPreparacion paso = convertirAEntidad(pasoDTO);
		PasoPreparacion pasoGuardado = pasoRepository.save(paso);
		return convertirADTO(pasoGuardado);
	}

	/**
	 * Actualizar un paso existente
	 */
	@Transactional
	public PasoPreparacionDTO actualizar(Integer id, PasoPreparacionDTO pasoDTO) {
		PasoPreparacion pasoExistente = pasoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paso de preparación no encontrado con ID: " + id));

		// Validar que la receta exista si se está cambiando
		if (!pasoExistente.getIdReceta().equals(pasoDTO.getIdReceta())) {
			if (!recetaRepository.existsById(pasoDTO.getIdReceta())) {
				throw new ResourceNotFoundException("Receta no encontrada con ID: " + pasoDTO.getIdReceta());
			}
		}

		// Validar que no exista otro paso con el mismo orden (excepto el actual)
		if (!pasoExistente.getOrden().equals(pasoDTO.getOrden())) {
			if (pasoRepository.existsByIdRecetaAndOrden(pasoDTO.getIdReceta(), pasoDTO.getOrden())) {
				throw new DuplicateResourceException(
						"Ya existe un paso con el orden " + pasoDTO.getOrden() + " en esta receta");
			}
		}

		// Actualizar campos
		pasoExistente.setIdReceta(pasoDTO.getIdReceta());
		pasoExistente.setOrden(pasoDTO.getOrden());
		pasoExistente.setDescripcionPaso(pasoDTO.getDescripcionPaso());

		PasoPreparacion pasoActualizado = pasoRepository.save(pasoExistente);
		return convertirADTO(pasoActualizado);
	}

	/**
	 * Eliminar un paso
	 */
	@Transactional
	public void eliminar(Integer id) {
		if (!pasoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Paso de preparación no encontrado con ID: " + id);
		}
		pasoRepository.deleteById(id);
	}

	/**
	 * Eliminar todos los pasos de una receta
	 */
	@Transactional
	public void eliminarPasosPorReceta(Integer idReceta) {
		if (!recetaRepository.existsById(idReceta)) {
			throw new ResourceNotFoundException("Receta no encontrada con ID: " + idReceta);
		}
		pasoRepository.deleteByIdReceta(idReceta);
	}

	/**
	 * Reordenar pasos de una receta Recibe una lista de IDs en el nuevo orden
	 * deseado
	 */
	@Transactional
	public List<PasoPreparacionDTO> reordenarPasos(Integer idReceta, List<Integer> nuevosOrdenesIds) {
		// Validar que la receta exista
		if (!recetaRepository.existsById(idReceta)) {
			throw new ResourceNotFoundException("Receta no encontrada con ID: " + idReceta);
		}

		// Obtener todos los pasos actuales
		List<PasoPreparacion> pasosActuales = pasoRepository.findByIdRecetaOrderByOrdenAsc(idReceta);

		if (pasosActuales.size() != nuevosOrdenesIds.size()) {
			throw new BusinessException("La cantidad de pasos no coincide");
		}

		// Actualizar el orden de cada paso
		for (int i = 0; i < nuevosOrdenesIds.size(); i++) {
			Integer idPaso = nuevosOrdenesIds.get(i);
			PasoPreparacion paso = pasoRepository.findById(idPaso)
					.orElseThrow(() -> new ResourceNotFoundException("Paso no encontrado con ID: " + idPaso));

			if (!paso.getIdReceta().equals(idReceta)) {
				throw new BusinessException("El paso " + idPaso + " no pertenece a la receta " + idReceta);
			}

			paso.setOrden(i + 1);
			pasoRepository.save(paso);
		}

		// Retornar los pasos reordenados
		return obtenerPasosPorReceta(idReceta);
	}

	/**
	 * Contar pasos de una receta
	 */
	@Transactional(readOnly = true)
	public Long contarPasos(Integer idReceta) {
		return pasoRepository.contarPasosPorReceta(idReceta);
	}

	// ========== MÉTODOS DE CONVERSIÓN ==========

	private PasoPreparacionDTO convertirADTO(PasoPreparacion paso) {
		PasoPreparacionDTO dto = new PasoPreparacionDTO();
		dto.setidPaso(paso.getIdPaso());
		dto.setIdReceta(paso.getIdReceta());
		dto.setOrden(paso.getOrden());
		dto.setDescripcionPaso(paso.getDescripcionPaso());

		// Agregar nombre de receta si está disponible
		if (paso.getReceta() != null) {
			dto.setnombreReceta(paso.getReceta().getNombreReceta());
		}

		return dto;
	}

	private PasoPreparacion convertirAEntidad(PasoPreparacionDTO dto) {
		PasoPreparacion paso = new PasoPreparacion();
		paso.setIdReceta(dto.getIdReceta());
		paso.setOrden(dto.getOrden());
		paso.setDescripcionPaso(dto.getDescripcionPaso());
		return paso;
	}
}
