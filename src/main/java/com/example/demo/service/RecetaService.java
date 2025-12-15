package com.example.demo.service;

import com.example.demo.dto.RecetaDTO;
import java.util.List;

public interface RecetaService {
	List<RecetaDTO> listarTodas();
    RecetaDTO obtenerPorId(Integer id);
    RecetaDTO guardar(RecetaDTO dto);
    RecetaDTO actualizar(Integer id, RecetaDTO dto);
    RecetaDTO actualizarParcial(Integer id, RecetaDTO dto);
    void eliminar(Integer id);
}