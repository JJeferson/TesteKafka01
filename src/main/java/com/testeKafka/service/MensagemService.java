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
import java.util.concurrent.ExecutionException;

@Service
public class MensagemService implements MensagemInterface{
    @Autowired
    MensagemRepository mensagemRepository;
    @Override
    public Mensagem SaveMensagem(Mensagem mensagem) throws ExecutionException, InterruptedException {
        Mensagem mensagemSalva = mensagemRepository.save(mensagem);
        EnviaMSG(mensagemSalva);
        return mensagemSalva;
    }

    @Override
    public List<Mensagem> getMensagem() {
        return mensagemRepository.findAll();
    }
    private void EnviaMSG(Mensagem mensagem) throws ExecutionException, InterruptedException {

        var producer = new KafkaProducer<String,String>(properties());
        var record = new ProducerRecord<>("Topico_De_Teste",
                mensagem.getIdMensagem(),mensagem.getTextoMensagem());

        producer.send(record,(data,ex) -> {
            if(ex != null){

            }
            System.out.println("Mensagem Enviada | Partição:"+data.partition()+" | OffSet:"+data.offset()+" | DataHora:"+data.timestamp());

        }).get();

    }

    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

}
