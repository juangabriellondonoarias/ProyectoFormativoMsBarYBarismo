package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
}
