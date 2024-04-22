package com.vivabicho.api.DTO;

import com.vivabicho.api.models.Especie;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AnimalDTO {

    private long id;
    private String nome;
    private long especieId;
    private String raca;
    private int idade;
    private String sexo;
    private String cor;
    private String tamanho;
    private double peso;
    private String descricao;
    private boolean disponivelParaAdocao;


}
