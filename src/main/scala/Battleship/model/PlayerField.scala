package Battleship.model

import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

//noinspection ScalaStyle
case class PlayerField(holder: Player, size: Int) {

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

  def printField(): String = {
    val stringOfField = new mutable.StringBuilder("")
    stringOfField ++= ("Field of: " + holder + "\n")
    for ((k, v) <- ListMap(playingField.toSeq.sortBy(_._1): _*))
      stringOfField ++= (k + " : " + v + "\n")
        .replace("ListBuffer", "")
        .replace("(", "")
        .replace(")", "")
        .replace(",", "")
        .replace("true", "true ")
    stringOfField.toString()
  }

  def replaceEntry(row: Int, column: Int, boolean: Boolean): Unit = {
    row match {
      case 0 => listA0.update(column, boolean)
      case 1 => listA1.update(column, boolean)
      case 2 => listA2.update(column, boolean)
      case 3 => listA3.update(column, boolean)
      case 4 => listA4.update(column, boolean)
      case 5 => listA5.update(column, boolean)
      case 6 => listA6.update(column, boolean)
      case 7 => listA7.update(column, boolean)
      case 8 => listA8.update(column, boolean)
      case 9 => listA9.update(column, boolean)
    }
  }

  def getEntry(row: Int, column: Int): Boolean = {
    row match {
      case 0 => listA0(column)
      case 1 => listA1(column)
      case 2 => listA2(column)
      case 3 => listA3(column)
      case 4 => listA4(column)
      case 5 => listA5(column)
      case 6 => listA6(column)
      case 7 => listA7(column)
      case 8 => listA8(column)
      case 9 => listA9(column)
    }
  }
}