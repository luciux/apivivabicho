package com.vivabicho.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "especie", fetch = FetchType.LAZY)
    private List<Animal> animais;

    @Override
    public String toString() {
        return "Especie{id=" + id + ", nome='" + nome + "', descricao='" + descricao + "'}";
    }
}
