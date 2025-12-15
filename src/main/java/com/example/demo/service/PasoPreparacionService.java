package com.example.demo.service;

import com.example.demo.dto.PasoPreparacionDTO;
import java.util.List;

public interface PasoPreparacionService {
	List<PasoPreparacionDTO> listarPorReceta(Integer idReceta);
    PasoPreparacionDTO obtenerPorId(Integer id);
    PasoPreparacionDTO guardar(PasoPreparacionDTO dto);
    PasoPreparacionDTO actualizar(Integer id, PasoPreparacionDTO dto);
    void eliminar(Integer id);
}
