package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "INGREDIENTE")
public class Ingrediente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Integer idIngrediente;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "cantidad", nullable = false, length = 100)
    private int cantidad;
    
    @Column(name = "unidad_medida_stock", length = 20)
    private String unidadMedidaStock;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta")
    private Receta receta;
    
    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getUnidadMedidaStock() {
        return unidadMedidaStock;
    }

    public void setUnidadMedidaStock(String unidadMedidaStock) {
        this.unidadMedidaStock = unidadMedidaStock;
    }
}
