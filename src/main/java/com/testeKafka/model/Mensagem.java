package com.testeKafka.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Document(collection="HistoricoDeMensagem")
public class Mensagem {

    @Id
    @JsonProperty("idMensagem")
    private String idMensagem;
    private String textoMensagem;
}
