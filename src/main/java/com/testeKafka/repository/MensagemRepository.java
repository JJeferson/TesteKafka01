package com.testeKafka.repository;

import com.testeKafka.model.Mensagem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MensagemRepository extends MongoRepository<Mensagem,String> {
}
