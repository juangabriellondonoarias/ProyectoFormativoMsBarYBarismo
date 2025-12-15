package com.example.demo.dto;

public class DetalleComandaDTO {

    private Integer idDetalleBarYBarismo;
    private Integer idReceta;
    private Integer cantidad;
    private String notas;
    private String estadoBebida;

    // ===== GETTERS =====

    public Integer getIdDetalleBarYBarismo() {
        return idDetalleBarYBarismo;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public String getEstadoBebida() {
        return estadoBebida;
    }

    // ===== SETTERS =====

    public void setIdDetalleBarYBarismo(Integer idDetalleBarYBarismo) {
        this.idDetalleBarYBarismo = idDetalleBarYBarismo;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setEstadoBebida(String estadoBebida) {
        this.estadoBebida = estadoBebida;
    }
}
