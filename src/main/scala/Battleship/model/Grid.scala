package Battleship.model

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Grid(size: Int){
  val matrix = Array.ofDim[Int](size,size)

  def getField(x: Int, y: Int): Int = {
    matrix(x)(y)
  }

  def setField(x: Int, y: Int, value: Int): Unit = {
    matrix(x)(y) = value
  }

  def getSize: Int = {
    size
  }

}
