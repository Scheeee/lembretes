package com.lembretes.uniamerica.lembretes.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Pessoa {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "campo não informado")
    @Column(name = "nomes")
    private String nome;

    @Getter @Setter
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Lembrete> lembretes;


}