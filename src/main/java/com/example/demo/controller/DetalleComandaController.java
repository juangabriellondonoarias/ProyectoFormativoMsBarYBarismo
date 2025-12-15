package com.example.demo.controller;

import java.util.List; 
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.DetalleComanda;
import com.example.demo.service.DetalleComandaService;


@RestController
@RequestMapping("/api/detalle-comanda-bar-y-barismo")
@Tag(name = "Detalles de Comandas", description = "Gestión de los ítems individuales (bebidas) dentro de una comanda de bar y barismo.")
public class DetalleComandaController {

	@Autowired
	private DetalleComandaService detalleComandaService;
	
    @Operation(summary = "Crear nuevo detalle de comanda", description = "Añade un ítem (bebida) a una comanda de bar y barismo existente.")
	@PostMapping
	public ResponseEntity<DetalleComanda>crear(@RequestBody DetalleComanda detalle){
		DetalleComanda nuevoDetalle = detalleComandaService.save(detalle);
		return ResponseEntity.ok(nuevoDetalle);
	}
	
    @Operation(summary = "Obtener todos los detalles", description = "Retorna una lista de todos los ítems de todas las comandas.")
	@GetMapping
	public List<DetalleComanda>obtener(){
		return DetalleComandaService.findAll();
	}
	
    @Operation(summary = "Obtener detalle por ID", description = "Retorna un ítem de comanda específico usando su ID.")
	@GetMapping("/{id}")
	public ResponseEntity<DetalleComanda> obtener(@PathVariable Integer id){
		DetalleComanda detalle = DetalleComandaService.findById(id);
		if(detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}
	
    @Operation(summary = "Eliminar detalle por ID", description = "Elimina un ítem específico de una comanda usando su ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		detalleComandaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
    @Operation(summary = "Actualización parcial de Cantidad ", description = "Actualiza solo la 'cantidad' de un ítem. Espera un JSON: {\"cantidad\": 3}.")
	@RequestMapping(value = "/{id}/cantidad", method = RequestMethod.PATCH)
	public ResponseEntity<DetalleComanda> actualizarCantidad(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
	    Integer cantidad = body.get("cantidad");
	    
	    if (cantidad == null || cantidad < 1) { 
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    DetalleComanda detalleActualizado = DetalleComandaService.actualizarCantidad(id, cantidad);

	    if (detalleActualizado != null) {
	        return ResponseEntity.ok(detalleActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
