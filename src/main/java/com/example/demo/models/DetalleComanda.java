package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLE_COMANDA_BAR_Y_BARISMO")
public class DetalleComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_comanda_bar_y_barismo")
    private Integer idDetalleComanda;

    /* Relación con la Comanda */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comanda_bar_y_barismo", nullable = false)
    private Comanda comanda;
    
    /* Relación con el Menú */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "notas", length = 200) // Nota: Si las notas son por plato, se mantiene aquí. Si son generales, deben ir solo en ComandaCocina.
    private String notas; 
    
    /* Constructor */
    public DetalleComanda() {
    }

    /* Getters y Setters */

    public Integer getIdDetalleComanda() {
        return idDetalleComanda;
    }

    public void setIdDetalleComanda(Integer idDetalleComanda) {
        this.idDetalleComanda = idDetalleComanda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
