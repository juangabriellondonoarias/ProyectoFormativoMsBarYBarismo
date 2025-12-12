package com.example.demo.service;

import com.example.demo.models.Menu;
import com.example.demo.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public List<Menu> findAll() {
        return repository.findAll();
    }
}
