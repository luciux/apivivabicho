package com.vivabicho.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "animais")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "especie_id")
    private Especie especie;
    private String raca;
    private int idade;
    private String sexo;
    private String cor;
    private String tamanho;
    private double peso;
    private String descricao;
    private boolean disponivelParaAdocao;

}
