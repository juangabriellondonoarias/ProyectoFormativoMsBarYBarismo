package com.example.demo.service;

import com.example.demo.dto.ComandaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mappers.ComandaMapper;
import com.example.demo.models.Comanda;
import com.example.demo.repository.ComandaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComandaService {

    private final ComandaRepository repository;
    private final ComandaMapper mapper;

    public ComandaDTO crear(ComandaDTO dto) {
        Comanda entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public ComandaDTO obtenerPorId(Integer id) {
        Comanda entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        return mapper.toDTO(entity);
    }

    public List<ComandaDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ComandaDTO actualizar(Integer id, ComandaDTO dto) {
        Comanda entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        entity.setIdMesa(dto.getIdMesa());
        entity.setEstado(Comanda.EstadoPedido.valueOf(dto.getEstado()));
        entity.setPrioridad(Comanda.Prioridad.valueOf(dto.getPrioridad()));

        return mapper.toDTO(repository.save(entity));
    }

    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido no encontrado");
        }
        repository.deleteById(id);
    }
}
