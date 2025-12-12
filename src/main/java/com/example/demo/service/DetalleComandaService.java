package com.example.demo.service;

import com.example.demo.dto.DetalleComandaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mappers.DetalleComandaMapper;
import com.example.demo.models.DetalleComanda;
import com.example.demo.models.Comanda;
import com.example.demo.repository.DetalleComandaRepository;
import com.example.demo.repository.ComandaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleComandaService {

    private final DetalleComandaRepository repository;
    private final ComandaRepository comandaRepository;
    private final DetalleComandaMapper mapper;

    public DetalleComandaDTO crear(Integer idPedido, DetalleComandaDTO dto) {

        Comanda comanda = comandaRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        DetalleComanda entity = mapper.toEntity(dto);
        entity.setPedidoCocina(comanda);

        return mapper.toDTO(repository.save(entity));
    }

    public List<DetalleComandaDTO> listarPorPedido(Integer idPedido) {
        Comanda comanda = comandaRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        return comanda.getDetalles().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void eliminar(Integer idDetalle) {
        if (!repository.existsById(idDetalle)) {
            throw new ResourceNotFoundException("Detalle no encontrado");
        }
        repository.deleteById(idDetalle);
    }
}
