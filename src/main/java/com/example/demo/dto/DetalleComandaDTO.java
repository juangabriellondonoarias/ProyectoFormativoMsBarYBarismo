package com.example.demo.dto;

import lombok.Data;

@Data
public class DetalleComandaDTO {

    private Integer idDetalleCocina;
    private Integer idReceta;
    private Integer cantidad;
    private String notas;
    private String estadoPlato;
}
