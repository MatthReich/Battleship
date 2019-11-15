package Battleship.controller

import Battleship.model.{Creator, Grid, Player}
import Battleship.util.Observable

import scala.collection.mutable

//noinspection ScalaStyle
class Controller() extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  val fieldSize: Int = 10
  val playerGrid_01: Grid = Grid(fieldSize)
  val playerGrid_02: Grid = Grid(fieldSize)
  var player_01 = Player("")
  var player_02 = Player("")
  var nr = Array[Int](1, 0, 0, 0)
  var nr2 = Array[Int](1, 0, 0, 0)




  def addShipUpper(player_01: Player, player_02: Player, playerGrid_01: Grid, playerGrid_02: Grid, nr: Array[Int], nr1: Array[Int]) {
    while ((nr(0) + nr(1) + nr(2) + nr(3)) != 0) {
      val tmp = addShips(playerGrid_01, player_01, nr)
      if (tmp(0) != 10) {
        playerGrid_01.setShip(tmp, nr)
      }
    }
    while ((nr1(0) + nr1(1) + nr1(2) + nr1(3)) != 0) {
      val tmp = addShips(playerGrid_02, player_02, nr1)
      if (tmp(0) != 10) {
        playerGrid_01.setShip(tmp, nr1)
      }
    }
  }

  def addShips(grid: Grid, player: Player, nr: Array[Int]): Array[Int] = {
    print(printGrid(grid, player))
    print(printNrOfShips(nr))
    val input = io.StdIn.readLine().split(" ")
    val tmp: Array[Int] = new Array[Int](4)
    try {
      if (input.length == 4) {
        var idx = 0
        while (idx < 4) {
          tmp(idx) = input(idx).toInt
          idx += 1
        }
      } else {
        print("Format Error\n")
        tmp(0) = 10
      }
    } catch {
      case e: NumberFormatException => println("you have to input numbers\n", e)
        tmp(0) = 10
    }
    if ((tmp(0) >= 0 && tmp(0) <= 9) && (tmp(1) >= 0 && tmp(1) <= 9) && (tmp(2) >= 0 && tmp(2) <= 9) && (tmp(3) >= 0 && tmp(3) <= 9)) {

    } else {
      tmp(0) = 10
    }
    tmp
  }

  def printGrid(grid: Grid, player: Player): String = {
    val stringOfGrid = new mutable.StringBuilder("")
    stringOfGrid ++= ("Field of: " + Console.GREEN + player.name + Console.RESET + "\n")
    stringOfGrid ++= "   "
    var ids = 0
    while (ids < grid.size) {
      stringOfGrid ++= "  " + ids + "  "
      ids += 1
    }
    stringOfGrid ++= "\n"
    var idy = 0
    while (idy < grid.size) {
      var idx = 0
      stringOfGrid ++= "A" + idy + " "
      while (idx < grid.size) {
        val tmp = grid.getField(idx, idy)
        tmp match {
          case 0 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
          case 1 => stringOfGrid ++= Console.GREEN + "  x  " + Console.RESET
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

  def printNrOfShips(nr: Array[Int]): String = {
    val string = new mutable.StringBuilder("")
    string ++= "Please set your Ships:\n"
    string ++= ("You can still place: " + Console.GREEN + nr(0) + Console.RESET + "x 2 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(1) + Console.RESET + "x 3 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(2) + Console.RESET + "x 4 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(3) + Console.RESET + "x 5 Block Ship\n")
    string.toString()
  }

}
