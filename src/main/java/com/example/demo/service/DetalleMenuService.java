package com.example.demo.service;

import com.example.demo.models.DetalleMenu;
import com.example.demo.repository.DetalleMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleMenuService {

    private final DetalleMenuRepository repository;

    public DetalleMenuService(DetalleMenuRepository repository) {
        this.repository = repository;
    }

    // Crear
    public DetalleMenu save(DetalleMenu detalle) {
        return repository.save(detalle);
    }

    // Obtener todos
    public List<DetalleMenu> findAll() {
        return repository.findAll();
    }

    // Obtener por id
    public DetalleMenu findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("DetalleMenu no encontrado con id " + id));
    }

    // Actualizar
    public DetalleMenu update(Long id, DetalleMenu detalleActualizado) {
        DetalleMenu existente = findById(id);

        // Se actualizan SOLO los campos que tiene tu entity
        existente.setImagen(detalleActualizado.getImagen());
        existente.setMenu(detalleActualizado.getMenu());
        existente.setIdReceta(detalleActualizado.getIdReceta());

        return repository.save(existente);
    }

    // Eliminar
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Buscar por ID del menú (menu.id)
    public List<DetalleMenu> findByMenuId(Long menuId) {
        return repository.findByMenuId(menuId);
    }
}
