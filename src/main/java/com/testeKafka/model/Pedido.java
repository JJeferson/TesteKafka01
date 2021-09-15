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
@Document(collection="Pedidos")
public class Pedido {
    @Id
    @JsonProperty("idPedido")
    private String  idPedido;
    private String  NomeCliente;
    private Integer Total;

}
