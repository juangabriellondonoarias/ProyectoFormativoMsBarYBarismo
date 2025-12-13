package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ComandaDTO {

    private Integer idPedidoCocina;
    private Integer idPedidoRestaurante;
    private Integer idMesa;
    private LocalDateTime horaEntrada;
    private String estado;
    private String prioridad;
    private List<DetalleComandaDTO> detalles;

    // ===== GETTERS =====

    public Integer getIdPedidoCocina() {
        return idPedidoCocina;
    }

    public Integer getIdPedidoRestaurante() {
        return idPedidoRestaurante;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public String getEstado() {
        return estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public List<DetalleComandaDTO> getDetalles() {
        return detalles;
    }

    // ===== SETTERS =====

    public void setIdPedidoCocina(Integer idPedidoCocina) {
        this.idPedidoCocina = idPedidoCocina;
    }

    public void setIdPedidoRestaurante(Integer idPedidoRestaurante) {
        this.idPedidoRestaurante = idPedidoRestaurante;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setDetalles(List<DetalleComandaDTO> detalles) {
        this.detalles = detalles;
    }
}
