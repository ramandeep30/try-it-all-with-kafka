package model

import play.api.libs.json._

case class Employee(id: String, name: String, salary: Long, manager: String) {

  def toJson: JsObject = Json.toJson(this).as[JsObject]
}


object Employee {

  def calculateSalary(salary: Long): Long = salary * 10 - 100
  implicit val format: Format[Employee] = Json.format
}
