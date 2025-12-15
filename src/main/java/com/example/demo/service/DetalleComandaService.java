package com.example.demo.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.DetalleComanda;
import com.example.demo.repository.DetalleComandaRepository;

@Service
public class DetalleComandaService {

	@Autowired
	private DetalleComandaRepository detalleComandaRepository;
	
	/*para guardar y actualizar*/
	@Transactional
	public DetalleComanda save(DetalleComanda detalle) {
		return detalleComandaRepository.save(detalle);
	}
	
	// Método para obtener todos los detalles de comanda
    // Usamos (readOnly = true) porque es solo lectura, y mantiene la sesión abierta 
    // para manejar la relación EAGER/LAZY si fuera necesario.
	
	@Transactional(readOnly = true)
	public List<DetalleComanda> findAll(){
		return detalleComandaRepository.findAll();	
		}
	
	/*metodo para obtener por el id*/
	@Transactional (readOnly = true)
	public DetalleComanda findById(Integer id) {
		return detalleComandaRepository.findById(id).orElse(null);
	}
	
	
	/*elimiar el id*/
	
	@Transactional
	public void deleteById(Integer id) {
		detalleComandaRepository.deleteById(id);
	}
	
	/*actualizar*/
	
	@Transactional
	public DetalleComanda actualizarCantidad(Integer id, Integer cantidad) {
	    // 1. Buscar el Detalle Comanda existente por ID
	    DetalleComanda detalleExistente = detalleComandaRepository.findById(id).orElse(null);
	    
	    if (detalleExistente != null) {
	        // 2. Modificar solo el campo 'cantidad'
	        
	        // ¡Importante! Asegúrate de que el setter en DetalleComandaCocina.java se llama setCantidad
	        detalleExistente.setCantidad(cantidad); 
	        
	        // 3. Guardar el objeto actualizado
	        return detalleComandaRepository.save(detalleExistente);
	    }
	    // Si no se encuentra, devuelve null
	    return null;
	}
}
