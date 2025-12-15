package com.example.demo.service;
import com.example.demo.dto.CategoriaRecetaDTO;
import java.util.List;
import java.util.Optional;

public interface CategoriaRecetaService {
	List<CategoriaRecetaDTO> listarTodas();
    CategoriaRecetaDTO obtenerPorId(Integer id);
    CategoriaRecetaDTO guardar(CategoriaRecetaDTO categoriaDTO);
    CategoriaRecetaDTO actualizar(Integer id, CategoriaRecetaDTO categoriaDTO);
    void eliminar(Integer id);
}