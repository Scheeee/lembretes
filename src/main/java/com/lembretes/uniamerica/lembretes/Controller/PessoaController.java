package com.lembretes.uniamerica.lembretes.Controller;

import com.lembretes.uniamerica.lembretes.Entity.Lembrete;
import com.lembretes.uniamerica.lembretes.Entity.Pessoa;
import com.lembretes.uniamerica.lembretes.Repository.PessoaRep;
import com.lembretes.uniamerica.lembretes.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRep pessoaRep;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{nome}")
    public ResponseEntity<?> FindByNome(@PathVariable("nome") String nome) {
        try {
            Pessoa pessoa = pessoaRep.getByNome(nome);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/lista")
    public ResponseEntity <?> getAll(){
        return ResponseEntity.ok(pessoaRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Pessoa pessoa){
        try {

            pessoaRep.save(pessoa);
                return ResponseEntity.ok("Pessoa cadastrada com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Pessoa pessoa){
        try {
            final Pessoa pessoa1 = this.pessoaRep.findById(id).orElse(null);

            if (pessoa1 == null || pessoa1.equals(pessoa.getId())){
                throw new RuntimeException("Não foi possivel indentificar a pessoa informada");
            }
            this.pessoaRep.save(pessoa);
            return ResponseEntity.ok
                    ("Pessoa Cadastrada com Sucesso");
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
