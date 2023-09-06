package com.lembretes.uniamerica.lembretes.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class Lembrete {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "campo não informado")
    @Column(name = "lembrete")
    private String lembrete;


    @Setter
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;


    public Lembrete(Long id, String lembrete, Pessoa pessoa) {
        this.id = id;
        this.lembrete = lembrete;
        this.pessoa = pessoa;
    }
}
