package Battleship.controller

import Battleship.model.{Creator, Grid, Player}
import Battleship.util.Observable

//noinspection ScalaStyle
class Controller() extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  val fieldSize: Int = 10
  val playerGrid_01: Grid = Grid(fieldSize)
  val playerGrid_02: Grid = Grid(fieldSize)
  var player_01 = Player("")
  var player_02 = Player("")
  var nr = Array[Int](2, 0, 0, 0)
  var nr2 = Array[Int](2, 0, 0, 0)


  def addShips(grid: Grid, player: Player, nr: Array[Int], input: String): Array[Int] = {
    val inputInner = input.split(" ")
    val tmp: Array[Int] = new Array[Int](4)
    try {
      if (inputInner.length == 4) {
        var idx = 0
        while (idx < 4) {
          tmp(idx) = inputInner(idx).toInt
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
    notifyObservers()
    tmp
  }

}
