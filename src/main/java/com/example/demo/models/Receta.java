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

	public Object getNombreReceta() {
		// TODO Auto-generated method stub
		return this.nombreReceta;
	}

	public void setIdCategoria(Object idCategoria2) {
		// TODO Auto-generated method stub
		
	}

	public void setIdModulo(Object idModulo2) {
		// TODO Auto-generated method stub
		
	}

	public void setNombreReceta(String nombreReceta2) {
		// TODO Auto-generated method stub
		
	}

	public void setTiempoPreparacion(Object tiempoPreparacion2) {
		// TODO Auto-generated method stub
		
	}

	public void setPorciones(Object porciones2) {
		// TODO Auto-generated method stub
		
	}

	public void setTemperatura(Object temperatura2) {
		// TODO Auto-generated method stub
		
	}

	public void setNotasAdicionales(Object notasAdicionales2) {
		// TODO Auto-generated method stub
		
	}

	public Object getIdReceta() {
		// TODO Auto-generated method stub
		return this.idReceta;
	}

	public Object getIdCategoria() {
		// TODO Auto-generated method stub
		return this.idCategoria;
	}

	public Object getTiempoPreparacion() {
		// TODO Auto-generated method stub
		return this.tiempoPreparacion;
	}

	public Object getIdModulo() {
		// TODO Auto-generated method stub
		return this.idModulo;
	}

	public Object getPorciones() {
		// TODO Auto-generated method stub
		return this.porciones;
	}

	public Object getTemperatura() {
		// TODO Auto-generated method stub
		return this.temperatura;
	}

	public Object getNotasAdicionales() {
		// TODO Auto-generated method stub
		return this.notasAdicionales;
	}


	
}