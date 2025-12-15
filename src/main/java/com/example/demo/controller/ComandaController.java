package com.example.demo.controller;

import java.util.List;  
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Comanda;
//import com.example.demo.models.EstadoComanda;
import com.example.demo.service.ComandaService;

/*import de swagger*/
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/comanda-bar-y-barismo")
@Tag(name = "Comandas de Bar y Barismo", description = "Gestión completa de órdenes, incluyendo estado, prioridad y notas.")

public class ComandaController {
	
	
@Autowired
private ComandaService comandaService;

/*crear*/
@Operation(summary = "Crear nueva comanda", description = "Registra una nueva orden en la comanda de bar y barismo con estado por defecto EN_ESPERA.")
@PostMapping
public ResponseEntity<Comanda> crearComanda(@RequestBody Comanda comanda){
	Comanda nuevaComanda = comandaService.save(comanda);
	return ResponseEntity.ok(nuevaComanda);
}

/*obtener las comandas*/
@Operation(summary = "Obtener todas las comandas", description = "Retorna una lista de todas las comandas de cocina.")
@GetMapping
public List<Comanda> obtener(){
	return comandaService.findAll();
}

/*obtener por el id*/
@Operation(summary = "Obtener comanda por ID", description = "Retorna una comanda específica usando su ID.")
@GetMapping("/{id}")
public ResponseEntity<Comanda> obtener(@PathVariable Integer id){
	Comanda comanda = comandaService.findById(id);
	if (comanda != null) {
		return ResponseEntity.ok(comanda);
	}
	return ResponseEntity.notFound().build();
}

/*eliminar por id*/
@Operation(summary = "Eliminar comanda", description = "Elimina una comanda de bar y barismo permanentemente usando su ID.")
@DeleteMapping("/{id}")
public ResponseEntity<Void>eliminarComanda(@PathVariable Integer id){
	comandaService.deleteById(id);
	return ResponseEntity.noContent().build();
}


///*actualizar*/
//@Operation(summary = "Actualiza el estado  ", description = "Actualiza el estado")
//@RequestMapping(value = "/{id}/estado", method = RequestMethod.PATCH) 
//public ResponseEntity<Comanda> actualizarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
//    try {
//        // Asumimos que el JSON viene como {"estado": "LISTO"}
//        String nuevoEstadoString = body.get("estado"); 
//        
//        // ... (El resto de tu lógica es correcta) ...
//        
//        if (nuevoEstadoString == null) {
//            return ResponseEntity.badRequest().body(null); 
//        }
//        
//        EstadoComanda nuevoEstado = EstadoComanda.valueOf(nuevoEstadoString.toUpperCase());
//        Comanda comandaActualizada = comandaService.actualizarEstado(id, nuevoEstado);
//
//        if (comandaActualizada != null) {
//            return ResponseEntity.ok(comandaActualizada);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(null); 
//    }
//}


@Operation(summary = "Actualización parcial de Notas ", description = "Actualiza solo las 'notas' ")
@RequestMapping(value = "/{id}/notas", method = RequestMethod.PATCH)
public ResponseEntity<Comanda> actualizarNotas(@PathVariable Integer id, @RequestBody Map<String, String> body) {
    String notas = body.get("notas"); 
    
    // Las notas pueden ser nulas o vacías si el usuario quiere borrarlas
    // Solo se chequea si la clave 'notas' no existe en el JSON
    if (!body.containsKey("notas")) {
        return ResponseEntity.badRequest().body(null); 
    }
    
    // Llamada al servicio
    Comanda comandaActualizada = comandaService.actualizarNotas(id, notas);

    if (comandaActualizada != null) {
        return ResponseEntity.ok(comandaActualizada);
    } else {
        return ResponseEntity.notFound().build();
    }
}


/*actualizar todo*/
@Operation(summary = "Actualización total ", description = "Reemplaza completamente una comanda existente. Se debe enviar el objeto completo (ID mesa, restaurante, estado, prioridad, notas).")
@PutMapping("/{id}")
public ResponseEntity<Comanda> actualizarComandaCompleta(@PathVariable Integer id, @RequestBody Comanda comandaActualizada) {
    
    // 1. Establecer el ID de la URL en el objeto recibido
    comandaActualizada.setIdComanda(id); // Asegúrate de que el setter se llame así
    
    // 2. Llamada al servicio
    Comanda resultado = comandaService.update(comandaActualizada);

    if (resultado != null) {
        return ResponseEntity.ok(resultado);
    } else {
        return ResponseEntity.notFound().build(); // Si el ID no existe
    }
}

}