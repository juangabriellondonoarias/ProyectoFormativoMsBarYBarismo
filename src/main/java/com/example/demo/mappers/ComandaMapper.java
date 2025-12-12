package com.example.demo.mappers;

import com.example.demo.dto.ComandaDTO;
import com.example.demo.models.Comanda;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ComandaMapper {

    private final DetalleComandaMapper detalleMapper;

    public PedidoCocinaMapper(DetalleComandaMapper detalleMapper) {
        this.detalleMapper = detalleMapper;
    }

    public ComandaDTO toDTO(Comanda comanda) {
        ComandaDTO dto = new ComandaDTO();
        dto.setIdPedidoCocina(comanda.getIdPedidoCocina());
        dto.setIdPedidoRestaurante(comanda.getIdPedidoRestaurante());
        dto.setIdMesa(comanda.getIdMesa());
        dto.setHoraEntrada(comanda.getHoraEntrada());
        dto.setEstado(comanda.getEstado().name());
        dto.setPrioridad(comanda.getPrioridad().name());

        if (comanda.getDetalles() != null) {
            dto.setDetalles(
                    comanda.getDetalles().stream()
                            .map(detalleMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public Comanda toEntity(ComandaDTO dto) {
        Comanda comanda= new Comanda();
        comanda.setIdPedidoRestaurante(dto.getIdPedidoRestaurante());
        comanda.setIdMesa(dto.getIdMesa());
        comanda.setEstado(Comanda.EstadoPedido.valueOf(dto.getEstado()));
        comanda.setPrioridad(Comanda.Prioridad.valueOf(dto.getPrioridad()));

        return comanda;
    }
}
