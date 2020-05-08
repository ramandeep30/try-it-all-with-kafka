package kafka.serde

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import model.User
import org.apache.kafka.common.serialization.Deserializer

class UserDeserializer extends Deserializer[User] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): User = {
    val byteIn = new ByteArrayInputStream(data)
    val objIn = new ObjectInputStream(byteIn)
    val obj = objIn.readObject().asInstanceOf[User]
    byteIn.close()
    objIn.close()
    obj
  }

  override def close(): Unit = { }
}
