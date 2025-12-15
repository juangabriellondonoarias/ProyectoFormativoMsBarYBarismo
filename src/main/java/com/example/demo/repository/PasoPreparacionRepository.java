package com.example.demo.repository;

import com.example.demo.models.PasoPreparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PasoPreparacionRepository extends JpaRepository<PasoPreparacion, Integer>{
	List<PasoPreparacion> findByReceta_IdRecetaOrderByOrdenAsc(Integer idReceta);
}
