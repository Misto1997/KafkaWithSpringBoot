# Kafka Connect With Spring Boot

## Introduction
We have plenty of example with Kafka Connector source(pull mechanism)/sink(push mechanism) but what if we don't have pull mechanism as source connector?
I have created this mini application where we want async writes to DB from our application once it's processed.

## Scenario
Our application is getting continues data via REST API and we don't need that data immediately but we need high latency.
As directly using Spring JPA makes writes synchronous, and acknowledgement may increase applications overall latency.

## Solution
Considering ACID properties and some mechanism to handle corrupt data we can use Kafka publisher from our application level to publish data async. to kafka topic and on the other side we can deploy confluent platform and can use Kafka JDBC sink connector to dump data to DB.

![img.png](img.png)

## Tech. stack and Tools used
1.  Java 8
2.  Spring boot
3.  Kafka
4.  Kafka Connector
5.  MySQL
6.  Postman

## Prerequisite
1.  JDK 8
2.  Kafka
3.  Confluent platform
4.  Maven    
5.  MySQL

## Get Started
```
1. Import below mentioned postman collections.
    - Kafka connect related api, get more details from here(https://docs.confluent.io/platform/current/connect/references/restapi.html)
        -https://www.getpostman.com/collections/a002367547dfa9368233
      
    - Data publisher API's
        -https://www.getpostman.com/collections/4e737aeeca8620ed6951
  
      
2. Start your confluent platform via below mentioned CLI command:
    -confluent local services start
        -this command will start below mentioned services with default port:
            1.  Zookeeper : port-2181
            2.  Kafka : port-9092
            3.  Schema Registry : port-8081
            4.  Kafka REST : port-8082
            5.  Connect : port-8083
            6.  KsqlDB : port-8088
   
3. created JDBC Sink connector from collection mentioned above for teacher and student topic.

4. Run Spring boot application

5. Post data from API from above mentioned collection for teacher and student.

6. if data is compliant, you can see data in your DB(school) and if data is not compliant then in DLQ topic.
```

## Note
    -You can use kafka connect either in Standalone mode(suitable for dev/testing) or in Distributed mode(suitable for Production) by changing config accordingly.

## References
```
1.  About Kafka Connect : https://docs.confluent.io/platform/current/connect/index.html?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.nonbrand_tp.rmkt_tgt.kafka-connectors_mt.xct_rgn.india_lng.eng_dv.all_con.kafka-connect&utm_term=kafka%20connect&creative=&device=c&placement=&gclid=Cj0KCQjw8vqGBhC_ARIsADMSd1AxnnWYkDnjlhCofAX1PsGA_WYz-u3MFFEPhy6y-pUoJo5KrEtW3rsaAhmNEALw_wcB, https://www.baeldung.com/kafka-connectors-guide
2.  About Kafka : https://kafka.apache.org/intro
3.  About Kafka with Spring : https://www.baeldung.com/spring-kafka
4.  JDBC Sink Connector : https://docs.confluent.io/kafka-connect-jdbc/current/sink-connector/index.html
```



