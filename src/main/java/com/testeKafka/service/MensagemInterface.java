package com.testeKafka.service;

import com.testeKafka.model.Mensagem;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public interface MensagemInterface {
    Mensagem SaveMensagem (Mensagem mensagem) throws ExecutionException, InterruptedException;
    List<Mensagem> getMensagem();
    void HouvinteMensagem();
}
