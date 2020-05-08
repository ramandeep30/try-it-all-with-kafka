package kafka.producer

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import model.User
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object UserCustomProducer extends App {

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
  properties.put("value.serializer", "kafka.serde.UserSerializer")

  val producer = new KafkaProducer[String, User](properties)

  def send(users: List[User]): Any = {
    try {
      for {
        user <- users
      } yield {
        producer.send(new ProducerRecord[String, User](topic, user.ssn, user)).get()
        print(s"\n-----> Sent record $user")
      }
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

  val records = List(User("1001", "Ramandeep", 27, "Female", "Indian"),
    User("1002", "Pam", 32, "Female", "American"),
    User("1003", "Angela", 35, "Female", "American"),
    User("104", "Dwight", 40, "Male", "American"),
    User("105", "Oscar", 45, "Male","Mexican")
  )

  send(records)
}
