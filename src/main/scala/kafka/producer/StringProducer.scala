package kafka.producer

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import model.Employee
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class StringProducer {

  val config: Config = ConfigFactory.load()

  val topic = config.getString("kafka.topic")
  val brokers = config.getString("kafka.brokers")

  val properties = new Properties()
  properties.put("bootstrap.servers", brokers)
  properties.put("acks", "all")
  properties.put("retries", "0")
  properties.put("batch.size", "16384")
  properties.put("linger.ms", "1")
  properties.put("buffer.memory", "33554432")
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](properties)

  def send(employees: List[Employee]): Any = {
    try {
      for {
        employee <- employees
      } yield {
        val data = employee.toJson.toString()
        producer.send(new ProducerRecord[String, String](topic, employee.id, data)).get()
        print(s"\nData sent --- $data")
      }
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

}
