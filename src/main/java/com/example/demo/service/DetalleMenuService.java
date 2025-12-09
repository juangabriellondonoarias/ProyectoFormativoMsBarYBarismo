package com.example.demo.service;

import com.example.demo.entity.DetalleMenu;
import com.example.demo.repository.DetalleMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleMenuService {

    private final DetalleMenuRepository repository;

    public DetalleMenuService(DetalleMenuRepository repository) {
        this.repository = repository;
    }

    public DetalleMenu save(DetalleMenu detalle) {
        return repository.save(detalle);
    }

    public List<DetalleMenu> findAll() {
        return repository.findAll();
    }
}
