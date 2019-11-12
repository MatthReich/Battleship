package Battleship.model

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Grid(size: Int){

  val listA0: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA1: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA2: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA3: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA4: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA5: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA6: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA7: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA8: ListBuffer[Boolean] = ListBuffer.fill(size)(false)
  val listA9: ListBuffer[Boolean] = ListBuffer.fill(size)(false)

  val playingField: mutable.Map[String, ListBuffer[Boolean]] = mutable.Map(
    "A0" -> listA0,
    "A1" -> listA1,
    "A2" -> listA2,
    "A3" -> listA3,
    "A4" -> listA4,
    "A5" -> listA5,
    "A6" -> listA6,
    "A7" -> listA7,
    "A8" -> listA8,
    "A9" -> listA9
  )



}
