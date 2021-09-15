package com.testeKafka.service;

import com.testeKafka.model.Mensagem;

import java.util.List;
import java.util.Properties;

public interface MensagemInterface {
    Mensagem SaveMensagem (Mensagem mensagem);
    List<Mensagem> getMensagem();

}
