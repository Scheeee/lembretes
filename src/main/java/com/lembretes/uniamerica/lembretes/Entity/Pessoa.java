package com.lembretes.uniamerica.lembretes.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class Pessoa {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @NotBlank(message = "campo não informado")

    @Column(name = "nomes")
    private String nome;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Lembrete> lembretes;

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


}