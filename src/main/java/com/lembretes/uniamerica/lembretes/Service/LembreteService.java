package com.lembretes.uniamerica.lembretes.Service;

import com.lembretes.uniamerica.lembretes.Entity.Lembrete;
import com.lembretes.uniamerica.lembretes.Entity.Pessoa;
import com.lembretes.uniamerica.lembretes.Repository.LembreteRep;
import com.lembretes.uniamerica.lembretes.Repository.PessoaRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LembreteService {

    @Autowired
    final LembreteRep lembreteRep;

    @Autowired
    final PessoaRep pessoaRep;

    public LembreteService(LembreteRep lembreteRep, PessoaRep pessoaRep) {
        this.lembreteRep = lembreteRep;
        this.pessoaRep = pessoaRep;
    }


}
