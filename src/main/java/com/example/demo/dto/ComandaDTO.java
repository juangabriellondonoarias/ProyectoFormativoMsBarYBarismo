package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComandaDTO {

    private Integer idPedidoCocina;
    private Integer idPedidoRestaurante;
    private Integer idMesa;
    private LocalDateTime horaEntrada;
    private String estado;
    private String prioridad;

    private List<DetalleComandaDTO> detalles;
}
