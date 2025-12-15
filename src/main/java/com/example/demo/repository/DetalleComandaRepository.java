package com.example.demo.repository;

import com.example.demo.models.DetalleComanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleComandaRepository extends JpaRepository<DetalleComanda, Integer> {
}
