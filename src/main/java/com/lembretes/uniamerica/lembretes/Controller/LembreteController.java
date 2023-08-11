package com.lembretes.uniamerica.lembretes.Controller;

import com.lembretes.uniamerica.lembretes.Entity.Lembrete;
import com.lembretes.uniamerica.lembretes.Repository.LembreteRep;
import com.lembretes.uniamerica.lembretes.Repository.PessoaRep;
import com.lembretes.uniamerica.lembretes.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/lembrete")
public class LembreteController {
    @Autowired
    private LembreteRep lembreteRep;

    @Autowired
    private LembreteService lembreteService;


    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Lembrete lembrete){
        try {

                this.lembreteRep.save(lembrete);
                return ResponseEntity.ok("Lembrete cadastrado com sucesso!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Lembrete lembrete){
        try {
            final Lembrete lembrete1 = this.lembreteRep.findById(id).orElse(null);

            if (lembrete1 == null || lembrete1.equals(lembrete.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o lembrete informado");
            }
            this.lembreteRep.save(lembrete);
            return ResponseEntity.ok
                    ("Lembrete Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
