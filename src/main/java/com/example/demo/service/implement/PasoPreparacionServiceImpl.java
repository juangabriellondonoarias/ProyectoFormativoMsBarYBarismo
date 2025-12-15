package com.example.demo.service.implement;

import com.example.demo.dto.PasoPreparacionDTO;
import com.example.demo.mappers.PasoPreparacionMapper;
import com.example.demo.models.PasoPreparacion;
import com.example.demo.repository.PasoPreparacionRepository;
import com.example.demo.service.PasoPreparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasoPreparacionServiceImpl implements PasoPreparacionService {

    @Autowired
    private PasoPreparacionRepository repositorio;

    @Override
    public List<PasoPreparacionDTO> listarPorReceta(Integer idReceta) {
        return repositorio.findByReceta_IdRecetaOrderByOrdenAsc(idReceta).stream()
                .map(PasoPreparacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PasoPreparacionDTO obtenerPorId(Integer id) {
        PasoPreparacion paso = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Paso no encontrado con ID: " + id));
        return PasoPreparacionMapper.toDTO(paso);
    }

    @Override
    public PasoPreparacionDTO guardar(PasoPreparacionDTO dto) {
        // Validamos que venga el ID de la receta
        if (dto.getIdReceta() == null) {
            throw new RuntimeException("Es necesario especificar el ID de la receta para crear un paso.");
        }
        PasoPreparacion paso = PasoPreparacionMapper.toEntity(dto);
        return PasoPreparacionMapper.toDTO(repositorio.save(paso));
    }

    @Override
    public PasoPreparacionDTO actualizar(Integer id, PasoPreparacionDTO dto) {
        PasoPreparacion pasoExistente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Paso no encontrado con ID: " + id));

        // Actualizamos campos
        pasoExistente.setOrden(dto.getOrden());
        pasoExistente.setDescripcionPaso(dto.getDescripcionPaso());
        // Nota: Normalmente no cambiamos un paso de una receta a otra (idReceta), así que no lo actualizamos aquí

        return PasoPreparacionMapper.toDTO(repositorio.save(pasoExistente));
    }

    @Override
    public void eliminar(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Paso no encontrado con ID: " + id);
        }
        repositorio.deleteById(id);
    }

}
