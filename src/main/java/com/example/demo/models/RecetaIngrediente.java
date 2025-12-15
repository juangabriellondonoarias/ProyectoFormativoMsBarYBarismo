package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RECETA_INGREDIENTE")
public class RecetaIngrediente {

	@EmbeddedId
	private RecetaIngredientePK id = new RecetaIngredientePK();
	
	// Relacion de vuelta a la receta
	@ManyToOne
	@MapsId("idReceta") // Mapea parte de la clave compuesta
	@JoinColumn(name = "id_receta")
	@JsonIgnore // Evita bucles infinitos
	private Receta receta;
	
	//Relacion hacia el ingrediente del catalogo
	@ManyToOne
	@MapsId("idIngrediente") // Mapea la otra parte de la clave
	@JoinColumn(name = "id_ingrediente")
	private Ingrediente ingrediente;
	
	@Column(name = "cantidad_requerida", precision = 10, scale = 2)
	private BigDecimal cantidadRequerida;
	
	@Column(name = "unidad_medida", length = 50)
	private String unidadMedida;
	
	 public RecetaIngredientePK getId() {
	        return id;
	    }

	    public void setId(RecetaIngredientePK id) {
	        this.id = id;
	    }

	    public Receta getReceta() {
	        return receta;
	    }

	    public void setReceta(Receta receta) {
	        this.receta = receta;
	    }

	    public Ingrediente getIngrediente() {
	        return ingrediente;
	    }

	    public void setIngrediente(Ingrediente ingrediente) {
	        this.ingrediente = ingrediente;
	    }

	    public BigDecimal getCantidadRequerida() {
	        return cantidadRequerida;
	    }

	    public void setCantidadRequerida(BigDecimal cantidadRequerida) {
	        this.cantidadRequerida = cantidadRequerida;
	    }

	    public String getUnidadMedida() {
	        return unidadMedida;
	    }

	    public void setUnidadMedida(String unidadMedida) {
	        this.unidadMedida = unidadMedida;
	    }
}
