package com.example.demo.models;

import java.time.LocalDateTime;   
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMANDA_COCINA")
public class Comanda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comanda_bar_y_barismo")
	private Integer idComanda;
	
	@Column(name = "id_comanda_restaurante", nullable = false)
	private Integer idComandaRestaurante;
	
	@Column(name = "id_mesa" , nullable = false)
	private Integer idMesa;
	
	/* mapeo del datetime */
	@CreationTimestamp
	@Column(name = "hora_entrada" , nullable = false , updatable = false)
	private LocalDateTime horaEntrada;
	
	/* Mapeo del ENUM ESTADO */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoComanda estado = EstadoComanda.EN_ESPERA; 
	
    /* Mapeo del enum prioridad */
	@Enumerated(EnumType.STRING)
	@Column(name = "prioridad" , nullable = false) 
	private PrioridadComanda prioridad = PrioridadComanda.MEDIA;
    
    /* Campo para notas/comentarios */
    @Column(name = "notas", length = 500) // Se aumentó la longitud para notas
    private String notas;

	/* constructor */
	public Comanda() {
	}
	
	/* get y set */
	
	public Integer getIdComanda() {
		return idComanda;
	}
	
	public Integer getIdComandaRestaurante() {
		return idComandaRestaurante;
	}
	
	public Integer getIdMesa () { 
		return idMesa;
	}
	
	public LocalDateTime getHoraEntrada () {
		return horaEntrada;
	}
	
	public EstadoComanda getEstado () {
		return estado;
	}
	
	public PrioridadComanda getPrioridad () {
		return prioridad;
	}
    
    public String getNotas() {
        return notas;
    }
	
	public void setIdComanda (Integer idComanda) {
		this.idComanda = idComanda;
	}
	
	public void setIdComandaRestaurante(Integer idComandaRestaurante) { // Corregido: se asume que este era el setter correcto
		this.idComandaRestaurante = idComandaRestaurante;
	}
	
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}
	
	public void setHoraEntrada(LocalDateTime horaEntrada ) {
		this.horaEntrada = horaEntrada;
	}
	
	public void setEstado( EstadoComanda estado) {
		this.estado = estado;
	}
	
	public void setPrioridad(PrioridadComanda prioridad) {
		this.prioridad = prioridad;
	}

    public void setNotas(String notas) { // Importante: setter de notas
        this.notas = notas;
    }
}
