package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_COMANDA")
public class DetalleComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_comanda")
    private Integer idDetalleComanda;

    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    @Column(name = "id_receta", nullable = false)
    private Integer idReceta;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(length = 200)
    private String notas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_plato")
    private EstadoPlato estadoPlato = EstadoPlato.EN_ESPERA;

    public enum EstadoPlato {
        EN_ESPERA, PREPARANDO, LISTO
    }

    // ===== GETTERS =====

    public Integer getIdDetalleComanda() {
        return idDetalleComanda;
    }

    public Comanda getComanda() {
        return comanda;
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

    public EstadoPlato getEstadoPlato() {
        return estadoPlato;
    }

    // ===== SETTERS =====

    public void setIdDetalleComanda(Integer idDetalleComanda) {
        this.idDetalleComanda = idDetalleComanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
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

    public void setEstadoPlato(EstadoPlato estadoPlato) {
        this.estadoPlato = estadoPlato;
    }
}
