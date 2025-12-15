package com.example.demo.models;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RecetaIngredientePK implements Serializable {


    @Column(name = "id_receta")
    private Integer idReceta;

    @Column(name = "id_ingrediente")
    private Integer idIngrediente;

    // ---------- GETTERS & SETTERS ----------

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

}
