package com.vivabicho.api.services;

import com.vivabicho.api.DTO.AnimalDTO;
import com.vivabicho.api.mappers.AnimalMapper;
import com.vivabicho.api.models.Animal;
import com.vivabicho.api.repositories.AnimalRepository;
import com.vivabicho.api.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository repository;

    private final AnimalMapper animalMapper;

    public AnimalService(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    public List<AnimalDTO> findAll(){
        return animalMapper.convertList(repository.findAll());
    }

    public AnimalDTO findById(Long id){
        Animal animal =  repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Id not found " + id)
        );
        return animalMapper.mapperToAnimalDTO(animal);
    }

    public AnimalDTO save(Animal animal){
        Animal animalRepo = repository.save(animal);
        return animalMapper.mapperToAnimalDTO(animalRepo);
    }

    public AnimalDTO update(Animal animal, Long id){
        Animal existingAnimal = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No records found for this ID!"));

        Animal updatedAnimal = repository.save(existingAnimal);
        return animalMapper.mapperToAnimalDTO(updatedAnimal);
    }
}
