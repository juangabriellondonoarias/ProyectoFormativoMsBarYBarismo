package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.DetalleMenu;

public interface DetalleMenuRepository extends JpaRepository<DetalleMenu, Long> {

}
