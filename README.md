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

## Tech. stack and Tool used
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

##Get Started
1. Import below mentioned postman collections.
    - Kafka connect related api, get more details from here(https://docs.confluent.io/platform/current/connect/references/restapi.html)
        -https://www.getpostman.com/collections/a002367547dfa9368233
      
    - Data publisher API's
        -https://www.getpostman.com/collections/4e737aeeca8620ed6951
      
2. Start your confluent platform via below mentioned CLI command:
    -confluent local services start
        -this command will start below mentioned services with default port:
            1.  Zookeeper
            2.  Kafka
            3.  Schema Registry
            4.  Kafka REST
            5.  Connect
            6.  KsqlDB
   
3. created JDBC Sink connector from collection mentioned above for teacher and student topic.

4. Run Spring boot application

5. Post data from API from above mentioned collection for teacher and student.

6. if data is compliant you can see data in your DB(school) and if data is not compliant then in DLQ topic.



