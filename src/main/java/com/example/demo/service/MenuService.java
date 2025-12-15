package com.example.demo.service;

import com.example.demo.models.Menu;
import com.example.demo.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    // Crear
    public Menu save(Menu menu) {
        menu.setActivo(true); // 🔥 regla de negocio clara
        return repository.save(menu);
    }

    // Obtener todos
    public List<Menu> findAll() {
        return repository.findAll();
    }

    // Obtener por id
    public Menu findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menú no encontrado con id: " + id)
                );
    }

    // Actualizar
    public Menu update(Long id, Menu datos) {
        Menu existente = findById(id);

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setCategoria(datos.getCategoria());
        // ⛔ NO tocar activo aquí, a menos que tengas un endpoint específico

        return repository.save(existente);
    }

    // Eliminación lógica (RECOMENDADO)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Menu no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

}
