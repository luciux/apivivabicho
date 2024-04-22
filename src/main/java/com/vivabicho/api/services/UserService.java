package com.vivabicho.api.services;

import com.vivabicho.api.DTO.UserDTO;
import com.vivabicho.api.models.User;
import com.vivabicho.api.repositories.UserRespository;
import com.vivabicho.api.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRespository repository;

    public List<UserDTO> findAll(){
        UserDTO userDTO = new UserDTO();
        return userDTO.convertList(repository.findAll());
    }

    public UserDTO findById(Long id){
        User user =  repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Id not found " + id)
        );
        UserDTO userDTO = new UserDTO();
        return userDTO.convert(user);
    }

    public UserDTO save(User user){
        User userExist = repository.findByName(user.getName());
        if(userExist != null){
            throw new RuntimeException("User already exists!");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        UserDTO userDTO = new UserDTO();
        User userRepo = repository.save(user);
        return userDTO.convert(userRepo);
    }

    public UserDTO update(User user){
        UserDTO userDTO = new UserDTO();
        User userUpdate = repository.findById(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("No records found for this ID!"));

        userUpdate.setName(user.getName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());

        return userDTO.convert(repository.save(userUpdate));
    }

    public void delete(Long id){
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
