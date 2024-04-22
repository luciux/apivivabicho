package com.vivabicho.api.controllers;

import com.vivabicho.api.DTO.AnimalDTO;
import com.vivabicho.api.mappers.AnimalMapper;
import com.vivabicho.api.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animal")
@CrossOrigin
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalMapper animalMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> save(@RequestBody AnimalDTO animalDTO){
        var animalSave = service.save(animalMapper.mapperToAnimal(animalDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(animalSave);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> update(@RequestBody AnimalDTO animalDTO, @PathVariable Long id){
        return ResponseEntity.ok(service.update(animalMapper.mapperToAnimal(animalDTO), id));
    }
}
