/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.controllers;

import Exceptions.TVANotFoundException;
import com.example.demo.Models.TVA;
import com.example.demo.Repo.TVARepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TVAController {
    private final TVARepository repository;

    public TVAController(TVARepository repository) {
        this.repository = repository;
    }

    @GetMapping("/TVA")
    List<TVA> all()
    {
        return repository.findAll();
    }

    @PostMapping("/TVA")
    TVA newTVA(@RequestBody TVA tva) {
        return repository.save(tva);
    }
    @GetMapping("/TVA/{id}")
    TVA one(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new TVANotFoundException(id));
    }
    @DeleteMapping("/TVA/{id}")
    void deleteTVA(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
