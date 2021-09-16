package com.testeKafka.service;

import com.testeKafka.model.Mensagem;
import com.testeKafka.repository.MensagemRepository;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public void HouvinteMensagem() {

        Boolean teste = true;
        var consumer = new KafkaConsumer<String,String>(propertiesListner());
        consumer.subscribe(Collections.singletonList("Topico_De_Teste"));
        while(teste.equals(true)) {
        var records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) {
                /*
                System.out.println("----------------------------------");
                System.out.println("Nada encontrado.");
                System.out.println("----------------------------------");
                 */
                }
            else{
                for (var record : records) {
                    Mensagem msgRecebida = new Mensagem();
                    msgRecebida.setTextoMensagem("KAFKA_MSG Recebida: " + record.value());
                    msgRecebida.setIdMensagem(record.key());

                    mensagemRepository.save(msgRecebida);
                    System.out.println("----------------------------------");
                    System.out.println("Nova MSG Recebida e Gravada" + msgRecebida.getTextoMensagem());
                    System.out.println("----------------------------------");
                    }
            }
        }
    }

    private void EnviaMSG(Mensagem mensagem) throws ExecutionException, InterruptedException {

        var producer = new KafkaProducer<String,String>(propertiesEnvio());
        var record = new ProducerRecord<>("Topico_De_Teste",
                mensagem.getIdMensagem(),mensagem.getTextoMensagem());

        producer.send(record,(data,ex) -> {
            if(ex != null){
            ex.printStackTrace();
                System.out.println("----------------------------------");
                System.out.println("ERRO");
                System.out.println("----------------------------------");
            }
            System.out.println("----------------------------------");
            System.out.println("Mensagem Enviada:"+mensagem.getTextoMensagem()+"  | Partição:"+data.partition()+" | OffSet:"+data.offset()+" | DataHora:"+data.timestamp());
            System.out.println("----------------------------------");
        }).get();

    }

    private static Properties propertiesEnvio(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }


    private static Properties propertiesListner(){
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,Mensagem.class.getSimpleName());
        return properties;
    }

}
