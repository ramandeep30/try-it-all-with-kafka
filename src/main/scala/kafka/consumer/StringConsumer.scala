package kafka.consumer

import java.time.Duration
import java.util
import java.util.Properties

import scala.collection.JavaConverters._
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}

class StringConsumer {

  val config: Config = ConfigFactory.load()
  val brokers = config.getString("kafka.brokers")
  val topic = config.getString("kafka.topic")
  val groupId = config.getString("kafka.groupId")

  val properties = new Properties()
  properties.put("bootstrap.servers", brokers)
  properties.put("group.id", groupId)
  properties.put("enable.auto.commit", "true")
  properties.put("auto.commit.interval.ms", "1000")
  properties.put("session.timeout.ms", "30000")
  properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val consumer = new KafkaConsumer[String, String](properties)
  consumer.subscribe(util.Collections.singletonList(topic))

  def consume = {
    while(true) {
      val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(30))
      records.asScala.foreach { record =>
        println(s"Received : ${record.value()} ::: At Partition : ${record.partition()} ::: At offset : ${record.offset()}")
      }
    }
  }
}
