package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_menu")
public class DetalleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen; // guardamos URL, no BLOB

    // RELACIÓN con MENU (muchos detalles → un menú)
    @ManyToOne
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    // Temporal: relación hacia receta como ID
    @Column(name = "id_receta")
    private Long idReceta;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }
}
