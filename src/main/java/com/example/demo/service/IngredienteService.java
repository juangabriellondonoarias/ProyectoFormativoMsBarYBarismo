package com.example.demo.service;

import com.example.demo.dto.IngredienteDTO;
import java.util.List;

public interface IngredienteService {
    List<IngredienteDTO> listarTodos();
    IngredienteDTO obtenerPorId(Integer id);
    IngredienteDTO guardar(IngredienteDTO ingredienteDTO);
    IngredienteDTO actualizar(Integer id, IngredienteDTO ingredienteDTO);
    IngredienteDTO actualizarStock(Integer id, int nuevaCantidad);
    void eliminar(Integer id);
}