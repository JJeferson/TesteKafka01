package com.testeKafka.repository;

import com.testeKafka.model.Mensagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MensagemRepository extends MongoRepository<Mensagem,String> {
}
