# Kafka Connect With Spring Boot

## Introduction
We have plenty of example with Kafka Connector source(pull mechanism)/sink(push mechanism) but what if we don't have pull mechanism as source connector?
I have created this mini application where we want async data transfer via push mechanism instead of pull mechanism using kafka publisher(with AVRO Schema) and dump using JDBC sink connector.

## Scenario
Our application is getting continues data via REST API and we don't need that data immediately but we need low latency.
Straigh forwad approach Spring JPA makes writes synchronous, and acknowledgement process may increase applications overall latency.

## Solution
Considering ACID properties and some mechanism to handle corrupt data we can use Kafka publisher(with AVRO schema) from our application level to publish data async. to kafka topic and on the other side we can deploy confluent platform and can use Kafka JDBC sink connector(with AVRO schema) to dump data to DB.

![img.png](img.png)

## Tech. Stack and Tools used
1.  Java 8
2.  Spring boot
3.  Kafka
4.  Kafka Connector
5.  MySQL
6.  Postman
7.  AVRO

## Prerequisite software/services/libraries
1.  JDK 8
2.  Kafka
3.  Confluent platform
4.  Maven    
5.  MySQL

## Get Started
```
1. Import below mentioned Postman Collections.
    - Kafka connect related API's, get more details from here(https://docs.confluent.io/platform/current/connect/references/restapi.html)
        -https://www.getpostman.com/collections/a002367547dfa9368233
      
    - Data publisher API's
        -https://www.getpostman.com/collections/4e737aeeca8620ed6951
  
      
2. Start your confluent platform locally via below mentioned CLI command:
    -confluent local services start
        -this command will start below mentioned services with default port:
            1.  Zookeeper : port-2181
            2.  Kafka : port-9092
            3.  Schema Registry : port-8081
            4.  Kafka REST : port-8082
            5.  Connect : port-8083
            6.  KsqlDB : port-8088
   
3. Create JDBC Sink connector from collection mentioned above for teacher and student topic.

4. Run Spring boot application

5. Send data via API from above mentioned collection for teacher and student.

6. if data is compliant, you can see data in your DB(school) and if data is not compliant then in DLQ(Dead Letter Queue) topic.
```

## Note
```
    -   You can change Sink as per your requirement. In this demo i have used JDBC sink Connector to dumb data to MySQL DB.
    -   This Demo is not intended to showcase Kafka's other feature and DLQ data handling, you can explore that own your own.
    -   You can use Kafka Connect either in Standalone mode(suitable for dev/testing) or in Distributed mode(suitable for Production) by changing config accordingly.
    -   This project runs on Standalone mode and in local system. You can change config accordingly to make it distributed and live.
    -   You can edit default ports to your desired ports via editing respective property files.
    -   You can explore other API's in Collection to get more details about connector's detail.
    -   Student and Teacher classes are auto generated from AVRO schema(under resources folder) via packaging the project.
```

## References
```
1.  About Kafka Connect : https://docs.confluent.io/platform/current/connect/index.html, https://www.baeldung.com/kafka-connectors-guide
2.  About Kafka : https://kafka.apache.org/intro
3.  About Kafka with Spring : https://www.baeldung.com/spring-kafka
4.  JDBC Sink Connector : https://docs.confluent.io/kafka-connect-jdbc/current/sink-connector/index.html
5.  About AVRO : https://avro.apache.org/docs/1.10.2/spec.html
```



