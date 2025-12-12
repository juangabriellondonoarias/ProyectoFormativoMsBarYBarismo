package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "COMANDA")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Integer idComanda;

    @Column(name = "id_pedido_restaurante", nullable = false)
    private Integer idPedidoRestaurante;

    @Column(name = "id_mesa", nullable = false)
    private Integer idMesa;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.EN_ESPERA;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad = Prioridad.MEDIA;

    @OneToMany(mappedBy = "pedidoCocina", cascade = CascadeType.ALL)
    private List<DetallePedidoComanda> detalles;

    public enum EstadoPedido {
        EN_ESPERA, EN_PREPARACION, LISTO, ENTREGADO
    }

    public enum Prioridad {
        BAJA, MEDIA, ALTA
    }
}
