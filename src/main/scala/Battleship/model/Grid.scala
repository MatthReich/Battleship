package Battleship.model

import Battleship.controller.PlayerStatus
import Battleship.controller.PlayerStatus.PlayerStatus

import scala.collection.mutable

//Standard  0
//Ship      1
//Hit       2
//Watter    3

case class Grid(size: Int) {
  private val matrix = Array.ofDim[Int](size, size)
  var ships: Array[Ship] = new Array[Ship](10)
  var shipSize = 0

  def getField(x: Int, y: Int): Int = {
    matrix(x)(y)
  }

  def setShip(array: Array[Int], nr: Array[Int]): Array[Int] = {
    var test: Boolean = true
    var idx = 0
    while (idx != shipSize) {
      if (ships(idx).colidate(array)) {
        test = false
      }
      idx += 1
    }
    if (test && ((array(0) == array(2)) ^ (array(1) == array(3)))) {
      var sizeShip = 0
      if (array(0) == array(2)) {
        sizeShip = array(3) - array(1) + 1
      } else {
        sizeShip = array(2) - array(0) + 1
      }

      if (sizeShip <= 5 && sizeShip >= 2) {
        if (nr(sizeShip - 2) != 0) {
          ships(shipSize) = Ship(array)
          ships(shipSize).setCoordinates()
          addToGrid(array)
          shipSize += 1
          nr(sizeShip - 2) -= 1
          print("New Ship\n")
        } else {
          print("No new Ship\n")
        }
      }
    }
    nr
  }

  def addToGrid(array: Array[Int]): Unit = {
    if (array(0) == array(2)) {
      var idx = array(1)
      while (idx <= array(3)) {
        setField(array(0), idx, 1)
        idx += 1
      }
    } else {
      var idx = array(0)
      while (idx <= array(2)) {
        setField(idx, array(1), 1)
        idx += 1
      }
    }
  }

  def setField(x: Int, y: Int, value: Int): Unit = {
    matrix(x)(y) = value
  }

  def getValue(x: Int, y:Int): Int = {
    matrix(x)(y)
  }

  def winStatement(): Boolean = {
    var statement = true
    // while for efficiency if found direct finished with search
    for (i <- 0 until size) {
      for (j <- 0 until size) {
        if (matrix(i)(j) == 1) statement = false
      }
    }
    statement
  }

  //noinspection ScalaStyle
  def toString(player: Player, sortOfPrint: Boolean, playerStatus: PlayerStatus): String = { //sortOfPrint true = with setted ships
    val stringOfGrid = new mutable.StringBuilder("") // false = without setted ships

    playerStatus match {
      case PlayerStatus.PLAYER_ONE =>
        stringOfGrid ++= ("Field of: " + Console.GREEN + player.name + Console.RESET + "\n")
      case _ =>
        stringOfGrid ++= ("Field of: " + Console.CYAN + player.name + Console.RESET + "\n")
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
        val tmp = this.getField(idx, idy)
        if (sortOfPrint) {
          tmp match {
            case 0 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
            case 1 => stringOfGrid ++= Console.GREEN + "  x  " + Console.RESET
            case 2 => stringOfGrid ++= Console.RED + "  x  " + Console.RESET
            case 3 => stringOfGrid ++= Console.BLUE + "  0  " + Console.RESET
          }
        } else {
          tmp match {
            case 0 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
            case 1 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
            case 2 => stringOfGrid ++= Console.RED + "  x  " + Console.RESET
            case 3 => stringOfGrid ++= Console.BLUE + "  0  " + Console.RESET
          }
        }
        idx += 1
      }
      idy += 1
      stringOfGrid ++= "\n"
    }
    stringOfGrid.toString()
  }

}
