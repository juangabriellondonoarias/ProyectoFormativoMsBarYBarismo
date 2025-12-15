package com.example.demo.dto;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRecetaDTO {
	private Integer idCategoria;
	private String nombreCategoria;
	
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
}