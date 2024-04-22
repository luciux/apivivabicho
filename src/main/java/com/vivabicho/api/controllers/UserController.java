package com.vivabicho.api.controllers;

import com.vivabicho.api.DTO.UserDTO;
import com.vivabicho.api.models.User;
import com.vivabicho.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO userDTO =  service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> userDTOList = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> save(@RequestBody @Valid User userRequest) {
        var user = new User();
        BeanUtils.copyProperties(userRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
