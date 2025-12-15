package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "PASO_PREPARACION", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_receta", "orden"}) // Evita tener dos "Paso 1" en la misma receta
    }
)
public class PasoPreparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paso")
    private Integer idPaso;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "descripcion_paso", nullable = false, columnDefinition = "TEXT")
    private String descripcionPaso;

    
    // --- RELACIÓN CON RECETA ---
    // Muchos pasos pertenecen a Una Receta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    @JsonIgnore
    private Receta receta;
    
    public Integer getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Integer idPaso) {
        this.idPaso = idPaso;
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

