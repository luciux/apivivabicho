package com.vivabicho.api.services;

import com.vivabicho.api.DTO.EspecieDTO;
import com.vivabicho.api.models.Especie;
import com.vivabicho.api.repositories.EspecieRepository;
import com.vivabicho.api.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieService {

    @Autowired
    EspecieRepository repository;

    public List<EspecieDTO> findAll(){
        EspecieDTO especieDTO = new EspecieDTO();
        return especieDTO.convertList(repository.findAll());
    }

    public Especie findById(Long id){
        Especie especie =  repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Id not found " + id)
        );
        return especie;
    }

    public EspecieDTO save(Especie especie){
        EspecieDTO especieDTO = new EspecieDTO();
        Especie especieRepo = repository.save(especie);
        return especieDTO.convertToDto(especieRepo);
    }

    public EspecieDTO update(Especie especie, Long id){
        EspecieDTO especieDTO = new EspecieDTO();
        Especie especieUpdate = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No records found for this ID!"));

        especieUpdate.setNome(especie.getNome());
        especieUpdate.setDescricao(especie.getDescricao());

        return especieDTO.convertToDto(repository.save(especieUpdate));
    }

}
