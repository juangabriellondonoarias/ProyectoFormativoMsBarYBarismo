package com.example.demo.service;

import java.util.List;  

import javax.swing.plaf.basic.BasicComboBoxUI.ListDataHandler;

import com.example.demo.models.Comanda;
//import com.example.demo.models.EstadoComanda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ComandaRepository;

//import jakarta.transaction.TransactionScoped;

@Service
public class ComandaService {

	@Autowired
	private ComandaRepository comandaRepository;
	
	/*metodo para guardar o actualizar la comanda*/
	@Transactional
	public Comanda save(Comanda comanda) {
		return comandaRepository.save(comanda);
	}
	
	
	
	/*metodo para obtener por el id*/
	@Transactional(readOnly = true)
	public Comanda findById(Integer id) {
		return comandaRepository.findById(id).orElse(null);
	}
	
	/*para obtener todas*/
	
	@Transactional(readOnly = true)
	public List<Comanda> findAll(){
		return comandaRepository.findAll();	
		}
	
	/*metodo para eliminar por el id*/
	
	@Transactional
	public void deleteById(Integer id) {
		comandaRepository.deleteById(id);
	}
	
//	@Transactional
//    public Comanda actualizarEstado(Integer id, EstadoComanda nuevoEstado) {
//        // 1. Buscar la comanda existente por ID
//        Comanda comandaExistente = comandaRepository.findById(id).orElse(null);
//        
//        if (comandaExistente != null) {
//            // 2. Modificar solo el campo de estado
//            comandaExistente.setEstado(nuevoEstado);
//            
//            // 3. Guardar el objeto actualizado (Hibernate sabe que solo el campo 'estado' cambió)
//            return comandaRepository.save(comandaExistente);
//        }
//        // Si no se encuentra, devuelve null
//        return null;
//    }
	
	@Transactional
	public Comanda actualizarNotas(Integer id, String notas) {
	    Comanda comandaExistente = comandaRepository.findById(id).orElse(null);
	    
	    if (comandaExistente != null) {
	        // Asegúrate de que setNotas(String) existe en tu entidad ComandaCocina.java
	        comandaExistente.setNotas(notas); 
	        return comandaRepository.save(comandaExistente);
	    }
	    return null;
	}
	
	
	@Transactional
	public Comanda update(Comanda comanda) {
	    // El PUT requiere que el ID exista para actualizar
	    if (comandaRepository.existsById(comanda.getIdComanda())) {
	        // save() con un ID existente realiza el UPDATE en JPA
	        return comandaRepository.save(comanda);
	    }
	    // Si el ID no existe, devuelve null
	    return null;
	}
}
