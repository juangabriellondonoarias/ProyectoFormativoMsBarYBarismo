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
        return repository.save(menu);
    }

    // Obtener todos
    public List<Menu> findAll() {
        return repository.findAll();
    }

    // Obtener por id
    public Menu findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu no encontrado con id: " + id));
    }

    // Actualizar
    public Menu update(Long id, Menu menuActualizado) {
        Menu existente = findById(id);

        existente.setNombre(menuActualizado.getNombre());
        existente.setDescripcion(menuActualizado.getDescripcion());
        existente.setPrecio(menuActualizado.getPrecio());
        existente.setActivo(menuActualizado.getActivo());

        return repository.save(existente);
    }

    // Eliminar
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
