package com.lembretes.uniamerica.lembretes;
import com.lembretes.uniamerica.lembretes.Controller.LembreteController;
import com.lembretes.uniamerica.lembretes.Controller.PessoaController;
import com.lembretes.uniamerica.lembretes.Entity.Lembrete;
import com.lembretes.uniamerica.lembretes.Entity.Pessoa;
import com.lembretes.uniamerica.lembretes.Repository.LembreteRep;
import com.lembretes.uniamerica.lembretes.Repository.PessoaRep;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class PessoaTest {
    @MockBean
    PessoaRep pessoaRep;

    @MockBean
    LembreteRep lembreteRep;
    @Autowired
    private final PessoaController pessoaController = new PessoaController();
    @Autowired
    private final LembreteController controllerLembrete = new LembreteController();

    @BeforeEach
    void injectFindByIdPessoa(){
        Optional<Pessoa> pessoa = Optional.of(new Pessoa(1L,"Sche"));
        Mockito.when(pessoaRep.findById(1L)).thenReturn(pessoa);

    }
    @BeforeEach
    void injectFindByNome(){
        Pessoa pessoa = new Pessoa(1L,"Sche");
        Mockito.when(pessoaRep.getByNome("Sche")).thenReturn(pessoa);

    }
    @BeforeEach
    void injectFindByIdLembrete(){
        Pessoa pessoa = new Pessoa(1L,"Sche");
        Optional<Lembrete> lembrete = Optional.of(new Lembrete(1L, "lembrete",pessoa));
        Mockito.when(lembreteRep.findById(1L)).thenReturn(lembrete);
    }

    @Test
    void testFindByIdPessoa(){
        var pessoacontroller = pessoaController.findById(1L);
        Long id = pessoacontroller.getBody().get().getId().longValue();
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);

    }
    @Test
    void testFindByNome(){
        var pessoacontroller = pessoaController.FindByNome("Sche");
        String nome = pessoacontroller.getBody().getNome();

        System.out.println(nome);
        Assert.assertEquals("Sche", nome);
    }

    @Test
    void testEditarPessoa(){
        var pessoacontroller = pessoaController.findById(1L);
        Pessoa pessoa = pessoacontroller.getBody().get();
        pessoa.setNome("Gabi");
        String nome = pessoa.getNome();
        System.out.println(nome);
        Assert.assertEquals("Gabi", nome);

    }
    @Test
    void testDeletarPessoa(){
        var pessoacontroller = pessoaController.findById(1L);
        Pessoa pessoa = pessoacontroller.getBody().get();
        pessoaRep.delete(pessoa);
        Pessoa pessoaExcluida = pessoaRep.findById(1L).orElse(null);
        System.out.println(pessoaExcluida);
        Assert.assertNull(pessoaExcluida);


    }
    @Test
    void testFindByIdLembrete(){
        var lembretecontroller = controllerLembrete.findById(1L);
        Long id = lembretecontroller.getBody().getId().longValue();
        System.out.println(id);
        Assert.assertEquals(1L,id, 0);

    }






}
