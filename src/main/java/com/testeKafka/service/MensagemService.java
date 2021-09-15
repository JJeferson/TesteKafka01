package com.testeKafka.service;

import com.testeKafka.model.Mensagem;
import com.testeKafka.repository.MensagemRepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class MensagemService implements MensagemInterface{
    @Autowired
    MensagemRepository mensagemRepository;
    @Override
    public Mensagem SaveMensagem(Mensagem mensagem) {
        Mensagem mensagemSalva = mensagemRepository.save(mensagem);

        var producer = new KafkaProducer<String,String>(properties());
        var record = new ProducerRecord<String,String>("Topico_De_Teste",
                     mensagemSalva.getIdMensagem(),mensagemSalva.getTextoMensagem());

        producer.send(record);
        return mensagemSalva;
    }

    @Override
    public List<Mensagem> getMensagem() {
        return mensagemRepository.findAll();
    }

    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

}
