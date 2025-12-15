package com.example.demo.repository;

import com.example.demo.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    
    // Buscar por nombre exacto
    Optional<Receta> findByNombreReceta(String nombreReceta);
    
    // Buscar por categoría
    List<Receta> findByIdCategoria(Integer idCategoria);
    
    // Buscar por nombre (búsqueda parcial)
    @Query("SELECT r FROM Receta r WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Receta> buscarPorNombreParcial(@Param("nombre") String nombre);
    
    // Verificar si existe por nombre (para validaciones)
    boolean existsByNombreReceta(String nombreReceta);
    
    // Buscar recetas con tiempo de preparación menor o igual a un valor
    List<Receta> findByTiempoPreparacionLessThanEqual(Integer tiempoMaximo);
    
    // Obtener recetas con todos sus ingredientes y pasos (optimizado)
    @Query("SELECT DISTINCT r FROM Receta r " +
           "LEFT JOIN FETCH r.ingredientes " +
           "LEFT JOIN FETCH r.pasos " +
           "WHERE r.idReceta = :id")
    Optional<Receta> findByIdWithDetails(@Param("id") Integer id);
    
    // Listar todas las recetas con información básica de categoría y módulo
    @Query("SELECT r FROM Receta r " +
           "JOIN FETCH r.categoria")
    List<Receta> findAllWithCategoryAndModule();
}
