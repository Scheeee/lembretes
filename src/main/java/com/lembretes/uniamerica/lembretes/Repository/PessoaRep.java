package com.lembretes.uniamerica.lembretes.Repository;

import com.lembretes.uniamerica.lembretes.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRep extends JpaRepository<Pessoa, Long> {

    Pessoa getByNome(String nome);
}
