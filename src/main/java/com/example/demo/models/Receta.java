package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "RECETA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;
    
    @Column(name = "id_categoria", nullable = false)
    private Integer idCategoria;
    
    @Column(name = "id_modulo", nullable = false)
    private Integer idModulo;
    
    @Column(name = "nombre_receta", nullable = false, unique = true, length = 150)
    private String nombreReceta;
    
    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion;
    
    @Column(name = "porciones")
    private Integer porciones = 1;
    
    @Column(name = "temperatura", length = 50)
    private String temperatura;
    
    @Column(name = "notas_adicionales", columnDefinition = "TEXT")
    private String notasAdicionales;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private CategoriaReceta categoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo", insertable = false, updatable = false)
    private Modulo modulo;
    
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaIngrediente> ingredientes;
    
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PasoPreparacion> pasos;

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Integer getPorciones() {
        return porciones;
    }

    public void setPorciones(Integer porciones) {
        this.porciones = porciones;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public CategoriaReceta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReceta categoria) {
        this.categoria = categoria;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public List<RecetaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<RecetaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PasoPreparacion> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoPreparacion> pasos) {
        this.pasos = pasos;
    }

	

}