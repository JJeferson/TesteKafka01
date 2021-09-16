package com.testeKafka.controller;


import com.testeKafka.model.Mensagem;
import com.testeKafka.repository.MensagemRepository;
import com.testeKafka.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MensagemController {
    @Autowired
    MensagemService mensagemService;

    @Transactional
    @CacheEvict(value = "/nova_mensagem", allEntries = true)
    @PostMapping("/nova_mensagem")
    public ResponseEntity<Mensagem> novaMSG(@RequestBody Mensagem mensagem) throws ExecutionException, InterruptedException {
        Mensagem mensagemEnviada = mensagemService.SaveMensagem(mensagem);
        return ResponseEntity.ok(mensagemEnviada);
    }

    @GetMapping("/historico_de_mensagens")
    public ResponseEntity<List<Mensagem>> historicoMensagem()
    {
        List<Mensagem> listaMensagem= mensagemService.getMensagem();
        return ResponseEntity.ok(listaMensagem);
    }

    @Transactional
    @CacheEvict(value = "/inicia_ouvinte", allEntries = true)
    @PostMapping("/inicia_ouvinte")
    public ResponseEntity<String> inicia_ouvinte() {
        mensagemService.ouvinteMensagem();
        return ResponseEntity.ok("Houvinte Iniciado.");
    }

}
