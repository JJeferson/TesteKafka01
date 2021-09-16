# TesteKafka01
Implementação de teste com Kafka</br>
Projeto criado para estudo e testes com Kafka</br>
</br></br>
Recursos que estarão disponiveis: </br>
-Envio de msg </br>
-Recebe Msg (Houvinte)</br>
-Histórico de msg's no mongo </br> 
 
</br>
<h4>Requisitos</h4>
*Java11  </br>
*MongoDB </br>
*Apache Kafka Instalado(Docker img) </br>
</br>
-Instalando Kafka: </br>
Comando: docker pull bitnami/kafka </br>
(https://hub.docker.com/r/bitnami/kafka/) </br>
 </br>
-Acessar via cmd a pasta do projeto e rodar:  </br>
docker-compose up -d </br>
</br>
No docker vai subir uma instancia do kafka </br>
<img src="docker_kafka1.png"> </br>
</br></br>
[POST] Nova MSG </br>
</br>
curl --location --request POST 'http://localhost:8080/nova_mensagem' \
--header 'Content-Type: application/json' \
--data-raw '{
    "textoMensagem":"Mensagem teste msg2"
}'
</br>
[POST] Inicia ouvinte </br>
</br>
curl --location --request POST 'http://localhost:8080/inicia_ouvinte'
</br>
[GET] Lista Histórico de msg gravadas no mongo </br>
</br>
curl --location --request GET 'http://localhost:8080/historico_de_mensagens'
</br>