package kafka.producer

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import model.Employee
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class ByteProducer {

  val config: Config = ConfigFactory.load()

  val brokers = config.getString("kafka.brokers")
  val topic = config.getString("kafka.topic")

  val properties = new Properties()
  properties.put("bootstrap.servers", brokers)
  properties.put("acks", "all")
  properties.put("retries", "0")
  properties.put("batch.size", "16384")
  properties.put("linger.ms", "1")
  properties.put("buffer.memory", "33554432")
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")

  val producer = new KafkaProducer[String, Array[Byte]](properties)

  def send(employees: List[Employee]): Any = {
    try {
      for {
        employee <- employees
      } yield {
      val data = employee.toString.getBytes()
      producer.send(new ProducerRecord[String, Array[Byte]](topic, employee.id, data)).get()
      print(s"\n-----> Sent record $data")
       }
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

}
