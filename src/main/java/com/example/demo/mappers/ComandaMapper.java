package com.example.demo.mappers;

import com.example.demo.dto.ComandaDTO;
import com.example.demo.models.Comanda;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ComandaMapper {

    private final DetalleComandaMapper detalleMapper;

    public ComandaMapper(DetalleComandaMapper detalleMapper) {
        this.detalleMapper = detalleMapper;
    }

    // ===== ENTITY -> DTO =====
    public ComandaDTO toDTO(Comanda comanda) {
        ComandaDTO dto = new ComandaDTO();

        dto.setIdPedidoBarYBarismo(comanda.getIdComanda());
        dto.setIdPedidoRestaurante(comanda.getIdPedidoRestaurante());
        dto.setIdMesa(comanda.getIdMesa());
        dto.setHoraEntrada(comanda.getHoraEntrada());

        if (comanda.getEstado() != null) {
            dto.setEstado(comanda.getEstado().name());
        }

        if (comanda.getPrioridad() != null) {
            dto.setPrioridad(comanda.getPrioridad().name());
        }

        if (comanda.getDetalles() != null) {
            dto.setDetalles(
                comanda.getDetalles().stream()
                    .map(detalleMapper::toDTO)
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // ===== DTO -> ENTITY =====
    public Comanda toEntity(ComandaDTO dto) {
        Comanda comanda = new Comanda();

        comanda.setIdPedidoRestaurante(dto.getIdPedidoRestaurante());
        comanda.setIdMesa(dto.getIdMesa());

        if (dto.getEstado() != null) {
            comanda.setEstado(
                Comanda.EstadoPedido.valueOf(dto.getEstado())
            );
        }

        if (dto.getPrioridad() != null) {
            comanda.setPrioridad(
                Comanda.Prioridad.valueOf(dto.getPrioridad())
            );
        }

        return comanda;
    }
}
