package com.vivabicho.api.mappers;

import com.vivabicho.api.DTO.AnimalDTO;
import com.vivabicho.api.models.Animal;
import com.vivabicho.api.models.Especie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalMapper {

    public AnimalDTO mapperToAnimalDTO(Animal animal) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setNome(animal.getNome());
        animalDTO.setEspecieId(animal.getEspecie().getId());
        animalDTO.setRaca(animal.getRaca());
        animalDTO.setIdade(animal.getIdade());
        animalDTO.setSexo(animal.getSexo());
        animalDTO.setCor(animal.getCor());
        animalDTO.setTamanho(animal.getTamanho());
        animalDTO.setPeso(animal.getPeso());
        animalDTO.setDescricao(animal.getDescricao());
        animalDTO.setDisponivelParaAdocao(animal.isDisponivelParaAdocao());
        return animalDTO;
    }

    public Animal mapperToAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setId(animalDTO.getId());
        animal.setNome(animalDTO.getNome());
        Especie especie = new Especie();
        especie.setId(animalDTO.getEspecieId());
        animal.setEspecie(especie);
        animal.setRaca(animalDTO.getRaca());
        animal.setIdade(animalDTO.getIdade());
        animal.setSexo(animalDTO.getSexo());
        animal.setCor(animalDTO.getCor());
        animal.setTamanho(animalDTO.getTamanho());
        animal.setPeso(animalDTO.getPeso());
        animal.setDescricao(animalDTO.getDescricao());
        animal.setDisponivelParaAdocao(animalDTO.isDisponivelParaAdocao());
        return animal;
    }

    public List<AnimalDTO> convertList(List<Animal> animalList){
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        animalList.forEach(animal -> {
            animalDTOList.add(this.mapperToAnimalDTO(animal));
        });
        return animalDTOList;
    }
}
