package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un paso de preparación de una receta
 * Cada paso tiene un orden específico y una descripción detallada
 */
@Entity
@Table(name = "PASO_PREPARACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasoPreparacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paso")
    private Integer idPaso;
    
    @Column(name = "id_receta", nullable = false)
    private Integer idReceta;
    
    @Column(name = "orden", nullable = false)
    private Integer orden;
    
    @Column(name = "descripcion_paso", nullable = false, columnDefinition = "TEXT")
    private String descripcionPaso;
    
    // Relación con Receta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", insertable = false, updatable = false)
    private Receta receta;

    public Integer getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Integer idPaso) {
        this.idPaso = idPaso;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDescripcionPaso() {
        return descripcionPaso;
    }

    public void setDescripcionPaso(String descripcionPaso) {
        this.descripcionPaso = descripcionPaso;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}

