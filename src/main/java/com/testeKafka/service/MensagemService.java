package com.testeKafka.service;

import com.testeKafka.model.Mensagem;
import com.testeKafka.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MensagemService implements MensagemInterface{
    @Autowired
    MensagemRepository mensagemRepository;
    @Override
    public Mensagem SaveMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @Override
    public List<Mensagem> getMensagem() {
        return mensagemRepository.findAll();
    }
}
