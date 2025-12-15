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

    @OneToMany(mappedBy = "pedidoBarYBarismo", cascade = CascadeType.ALL)
    private List<DetalleComanda> detalles;

    public enum EstadoPedido {
        EN_ESPERA, EN_PREPARACION, LISTO, ENTREGADO
    }

    public enum Prioridad {
        BAJA, MEDIA, ALTA
    }
    
    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public Integer getIdPedidoRestaurante() {
        return idPedidoRestaurante;
    }

    public void setIdPedidoRestaurante(Integer idPedidoRestaurante) {
        this.idPedidoRestaurante = idPedidoRestaurante;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

}
