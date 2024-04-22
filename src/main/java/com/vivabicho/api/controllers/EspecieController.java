package com.vivabicho.api.controllers;

import com.vivabicho.api.DTO.EspecieDTO;
import com.vivabicho.api.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/especie")
public class EspecieController {

    @Autowired
    private EspecieService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EspecieDTO> save(@RequestBody EspecieDTO especieDTO){
        return ResponseEntity.ok(service.save(especieDTO.convertToEspecie(especieDTO)));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EspecieDTO> update(@RequestBody EspecieDTO especieDTO, @PathVariable Long id){
        return ResponseEntity.ok(service.update(especieDTO.convertToEspecie(especieDTO), id));
    }
}
