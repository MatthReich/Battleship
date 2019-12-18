package Battleship.model.gridComponent.advancedGrid

import Battleship.controller.PlayerState
import Battleship.controller.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid

import scala.collection.mutable

case class Grid(size: Int) extends InterfaceGrid {
  private val matrix = Array.ofDim[Int](size, size)
  var shipSize = 0


  def winStatement(): Boolean = {
    var statement = true
    for (i <- 0 until size) {
      for (j <- 0 until size) {
        if (matrix(i)(j) == 1) statement = false
      }
    }
    statement
  }

  override def setField(x: Int, y: Int, value: Int): Unit = {
    matrix(x)(y) = value
  }

  override def getField(x: Int, y: Int): Int = {
    var tmp = matrix(x)(y)
    if (tmp == 1) {
      tmp = 0
    }
    tmp
  }

  override def getValue(x: Int, y:Int): Int = {
    matrix(x)(y)
  }

  override def toString(player: InterfacePerson, sortOfPrint: Boolean, playerStatus: PlayerState): String = { //sortOfPrint true = with setted ships
    val stringOfGrid = new mutable.StringBuilder("") // false = without setted ships

    playerStatus match {
      case PlayerState.PLAYER_ONE =>
        stringOfGrid ++= ("Field of: " + Console.GREEN + player.toString + Console.RESET + "\n")
      case _ =>
        stringOfGrid ++= ("Field of: " + Console.CYAN + player.toString + Console.RESET + "\n")
    }

    stringOfGrid ++= "   "
    var ids = 0
    while (ids < this.size) {
      stringOfGrid ++= "  " + ids + "  "
      ids += 1
    }
    stringOfGrid ++= "\n"
    var idy = 0
    while (idy < this.size) {
      var idx = 0
      stringOfGrid ++= "A" + idy + " "
      while (idx < this.size) {
        val tmp = this.getValue(idx, idy)
        tmp match {
          case 0 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
          case 1 =>
            if (sortOfPrint) {
              stringOfGrid ++= Console.GREEN + "  x  " + Console.RESET
            } else {
              stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
            }
          case 2 => stringOfGrid ++= Console.RED + "  x  " + Console.RESET
          case 3 => stringOfGrid ++= Console.BLUE + "  0  " + Console.RESET
        }
        idx += 1
      }
      idy += 1
      stringOfGrid ++= "\n"
    }
    stringOfGrid.toString()
  }

}
