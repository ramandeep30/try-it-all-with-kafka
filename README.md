# try-it-all-with-kafka

Apache Kafka is a well-known name in the world of Big Data. It is one of the most used distributed streaming platforms.
Kafka is just not a messaging queue but a full-fledged event streaming platform. It is a framework for storing, reading and analyzing streaming data. 
It is a publish-subscribe based durable messaging system exchanging data between processes, applications, and servers.


This repository contains the implementation for:
1. Kafka String Producer and Consumer
2. Kafka Byte Producer and Consumer
3. Custom Serializer and Deserializer

To run this application:

a. Create a topic sample (configurable- application.conf)

b. For Byte Data, run:
> sbt 

> runMain kafka.application.ByteDataApp

For String Data, run:
> sbt

> runMain kafka.application.StringDataApp

For Custom Data, run:
> sbt

> runMain kafka.application.UserDataApp


### To understand basics of Kafka, you can visit my blogs:
1. Apache Kafka- What & Why? - https://ramandeep2017.wordpress.com/2020/04/21/apache-kafka-what-why-and-how/
2. Apache Zookeeper- Does Kafka need it? - https://ramandeep2017.wordpress.com/2020/04/27/apache-zookeeper-does-kafka-need-it/
3. Apache Kafka: Topic Partitions, Replicas & ISR - https://ramandeep2017.wordpress.com/2020/05/03/apache-kafka-partitions-replicas-isr/
4. Serialization in Kafka - https://ramandeep2017.wordpress.com/2020/05/08/serialization-in-kafka/
