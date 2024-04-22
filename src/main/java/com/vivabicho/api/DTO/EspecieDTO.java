package com.vivabicho.api.DTO;

import com.vivabicho.api.models.Especie;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class EspecieDTO {

    private Long id;
    private String nome;
    private String descricao;

    public EspecieDTO() {
    }

    public EspecieDTO convertToDto(Especie especie) {
        this.id = especie.getId();
        this.nome = especie.getNome();
        this.descricao = especie.getDescricao();
        return this;
    }

    public Especie convertToEspecie(EspecieDTO especieDTO) {
        Especie especie = new Especie();
        especie.setNome(especieDTO.getNome());
        especie.setDescricao(especieDTO.getDescricao());
        return especie;
    }

    public List<EspecieDTO> convertList(List<Especie> especieList){
        EspecieDTO especieDTO = new EspecieDTO();
        List<EspecieDTO> especieDTOList = new ArrayList<>();
        especieList.forEach(especie -> {
            especieDTOList.add(especieDTO.convertToDto(especie));
        });
        return especieDTOList;
    }
}
