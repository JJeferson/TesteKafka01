package com.testeKafka.controller;


import com.testeKafka.model.Mensagem;
import com.testeKafka.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MensagemController {
    @Autowired
    MensagemRepository mensagemRepository;

    @Transactional
    @CacheEvict(value = "/nova_mensagem", allEntries = true)
    @PostMapping("/nova_mensagem")
    public ResponseEntity<Mensagem> novaMSG(@RequestBody Mensagem mensagem)
    {
        Mensagem mensagemEnviada = mensagemRepository.save(mensagem);
        return ResponseEntity.ok(mensagemEnviada);
    }

    @GetMapping("/historico_de_mensagens")
    public ResponseEntity<List<Mensagem>> historicoMensagem()
    {
        List<Mensagem> listaMensagem= mensagemRepository.findAll();
        return ResponseEntity.ok(listaMensagem);
    }
}
