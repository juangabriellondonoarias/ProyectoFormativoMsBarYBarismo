package com.example.demo.repository;

import com.example.demo.models.PasoPreparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasoPreparacionRepository extends JpaRepository<PasoPreparacion, Integer> {
    
    /**
     * Obtener todos los pasos de una receta ordenados
     */
    List<PasoPreparacion> findByIdRecetaOrderByOrdenAsc(Integer idReceta);
    
    /**
     * Obtener un paso específico de una receta
     */
    Optional<PasoPreparacion> findByIdRecetaAndOrden(Integer idReceta, Integer orden);
    
    /**
     * Verificar si existe un paso con ese orden en la receta
     */
    boolean existsByIdRecetaAndOrden(Integer idReceta, Integer orden);
    
    /**
     * Contar cuántos pasos tiene una receta
     */
    @Query("SELECT COUNT(p) FROM PasoPreparacion p WHERE p.idReceta = :idReceta")
    Long contarPasosPorReceta(@Param("idReceta") Integer idReceta);
    
    /**
     * Obtener el orden máximo de una receta
     * Útil para agregar un nuevo paso al final
     */
    @Query("SELECT MAX(p.orden) FROM PasoPreparacion p WHERE p.idReceta = :idReceta")
    Integer obtenerOrdenMaximo(@Param("idReceta") Integer idReceta);
    
    /**
     * Eliminar todos los pasos de una receta
     */
    void deleteByIdReceta(Integer idReceta);
    
    /**
     * Obtener pasos de una receta con información de la receta
     */
    @Query("SELECT p FROM PasoPreparacion p " +
           "JOIN FETCH p.receta " +
           "WHERE p.idReceta = :idReceta " +
           "ORDER BY p.orden ASC")
    List<PasoPreparacion> findByIdRecetaWithRecetaInfo(@Param("idReceta") Integer idReceta);
    
    /**
     * Buscar pasos que contengan cierto texto en su descripción
     */
    @Query("SELECT p FROM PasoPreparacion p " +
           "WHERE p.idReceta = :idReceta " +
           "AND LOWER(p.descripcionPaso) LIKE LOWER(CONCAT('%', :texto, '%')) " +
           "ORDER BY p.orden ASC")
    List<PasoPreparacion> buscarPorTextoEnDescripcion(
        @Param("idReceta") Integer idReceta,
        @Param("texto") String texto
    );
}
