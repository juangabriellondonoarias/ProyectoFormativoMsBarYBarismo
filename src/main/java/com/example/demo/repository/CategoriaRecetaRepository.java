package com.example.demo.repository;
import com.example.demo.models.CategoriaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRecetaRepository extends JpaRepository<CategoriaReceta, Integer> {

}