package kafka.application

import kafka.consumer.ByteConsumer
import kafka.producer.ByteProducer
import model.Employee

object ByteDataApp extends App {

  val byteProducer = new ByteProducer
  val byteConsumer = new ByteConsumer

  val records = List(
    Employee("101", "Ramandeep", 500000, "Andrew"),
    Employee("102", "Pam", 450000, "Micheal"),
    Employee("103", "Angela", 550000, "Micheal"),
    Employee("104", "Dwight", 500000, "Micheal"),
    Employee("105", "Oscar", 700000, "Micheal")
  )

  byteProducer.send(records)
  byteConsumer.consume()

}
