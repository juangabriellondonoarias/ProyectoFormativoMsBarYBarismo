package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.DetalleComanda;

@Repository
public interface DetalleComandaRepository extends JpaRepository<DetalleComanda, Integer> {
    
}
