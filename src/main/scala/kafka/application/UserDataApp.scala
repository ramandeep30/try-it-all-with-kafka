package kafka.application

import kafka.consumer.UserCustomConsumer
import kafka.producer.UserCustomProducer
import model.User

object UserDataApp extends App {

  val userProducer = new UserCustomProducer
  val userConsumer = new UserCustomConsumer

  val records = List(
    User("1001", "Ramandeep", 27, "Female", "Indian"),
    User("1002", "Pam", 32, "Female", "American"),
    User("1003", "Angela", 35, "Female", "American"),
    User("104", "Dwight", 40, "Male", "American"),
    User("105", "Oscar", 45, "Male","Mexican")
  )

  userProducer.send(records)
  userConsumer.consume
}
