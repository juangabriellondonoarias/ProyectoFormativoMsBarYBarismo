package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.DetalleMenu;

import java.util.List;

@Repository
public interface DetalleMenuRepository extends JpaRepository<DetalleMenu, Long> {

    // Buscar detalles por el ID del menú
    List<DetalleMenu> findByMenuId(Long menuId);
}