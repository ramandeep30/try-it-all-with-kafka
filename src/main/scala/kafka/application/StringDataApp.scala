package kafka.application

import kafka.consumer.StringConsumer
import kafka.producer.StringProducer
import model.Employee

object StringDataApp extends App {

  val stringProducer = new StringProducer
  val stringConsumer = new StringConsumer

  val records = List(
    Employee("101", "Ramandeep", 500000, "Andrew"),
    Employee("102", "Pam", 450000, "Micheal"),
    Employee("103", "Angela", 550000, "Micheal"),
    Employee("104", "Dwight", 500000, "Micheal"),
    Employee("105", "Oscar", 700000, "Micheal")
  )

  stringProducer.send(records)
  stringConsumer.consume
}
