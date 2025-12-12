package com.example.demo.repository;

import com.example.demo.models.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
}
