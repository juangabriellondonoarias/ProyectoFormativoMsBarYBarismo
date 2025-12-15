package com.example.demo.dto;

import java.util.List;

public class RecetaDTO {
	private Integer idReceta;
    private Integer idCategoria; // ID para vincular
    private String nombreReceta;
    private Integer tiempoPreparacion;
    private Integer porciones;
    private String temperatura;
    private String notasAdicionales;
    
    // Opcional: Nombres descriptivos para mostrar en listas sin hacer otra consulta
    private String nombreCategoria; 
    
    private List<PasoPreparacionDTO> pasos;
    private List<RecetaIngredienteDTO> ingrediente;
    
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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    public List<PasoPreparacionDTO> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoPreparacionDTO> pasos) {
        this.pasos = pasos;
    }

    public List<RecetaIngredienteDTO> getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(List<RecetaIngredienteDTO> ingrediente) {
        this.ingrediente = ingrediente;
    }
}