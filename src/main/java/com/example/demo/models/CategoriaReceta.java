package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIA_RECETA")
public class CategoriaReceta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
	private Integer idCategoria;
	
	@Column(name="nombre_categoria", nullable = false, unique=true, length = 100)
	private String nombreCategoria;
	
	/*@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Receta> recetas;*/
	
	public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

   /* public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }*/
}