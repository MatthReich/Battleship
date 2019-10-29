package Battleship.model

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class PlayingField(holder : Player, size : Int) {

  val listA0 = ListBuffer.fill(size) (false)
  val listA1 = ListBuffer.fill(size) (false)
  val listA2 = ListBuffer.fill(size) (false)
  val listA3 = ListBuffer.fill(size) (false)
  val listA4 = ListBuffer.fill(size) (false)
  val listA5 = ListBuffer.fill(size) (false)
  val listA6 = ListBuffer.fill(size) (false)
  val listA7 = ListBuffer.fill(size) (false)
  val listA8 = ListBuffer.fill(size) (false)
  val listA9 = ListBuffer.fill(size) (false)


  val playingField = mutable.Map (
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

  def printField () : String = {
    val stringOfField = new mutable.StringBuilder("")
    stringOfField ++= ("Field of: " + holder + "\n")
    for ((k, v) <- playingField)
      stringOfField ++= (k + " : " + v + "\n")
    stringOfField.toString()
  }

  def replaceEntry (row : Int, column : Int, boolean: Boolean) : Unit = {
    playingField("A0").update(column, boolean)
  }
}
