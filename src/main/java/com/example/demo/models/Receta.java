package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECETA")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;

    // Relación con Categoria
    @ManyToOne(fetch = FetchType.EAGER) // Eager para traer el nombre de la categoría fácil
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaReceta categoria;
    

    @Column(name = "nombre_receta", nullable = false, unique = true, length = 150)
    private String nombreReceta;

    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion; // Minutos

    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer porciones;

    @Column(length = 50)
    private String temperatura;

    @Column(name = "notas_adicionales", columnDefinition = "TEXT")
    private String notasAdicionales;
    
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PasoPreparacion> pasos;
	
	// Relación Inversa: Ingredientes (Si borro receta, se borran sus ingredientes)
    // Nota: Necesitas la entidad RecetaIngrediente creada para descomentar esto
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaIngrediente> ingredientes; 

   
    
    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public CategoriaReceta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReceta categoria) {
        this.categoria = categoria;
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

    public List<PasoPreparacion> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoPreparacion> pasos) {
        this.pasos = pasos;
    }
    
    public List<RecetaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<RecetaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}