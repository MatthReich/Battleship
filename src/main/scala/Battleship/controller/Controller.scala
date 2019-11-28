package Battleship.controller

import Battleship.model.{Creator, Grid, Player}
import Battleship.util.Observable

//noinspection ScalaStyle
class Controller(var grid_player01: Grid, var grid_player02: Grid) extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01 = Player("")
  var player_02 = Player("")
  var nr: Array[Int] = Array[Int](1, 0, 0, 0)
  var nr2: Array[Int] = Array[Int](1, 0, 0, 0)


  def addShips(grid: Grid, ship: Array[Int], nrArray: Array[Int]): Unit = {
    grid.setShip(ship, nrArray)
    notifyObservers()
  }

  def gridToString(int: Int, boolean: Boolean): String = {
    if (boolean) {
      int match {
        case 0 => grid_player01.toString(player_01, boolean)
        case 1 => grid_player02.toString(player_02, boolean)
      }
    } else {
      int match {
        case 0 => grid_player01.toString(player_01, boolean)
        case 1 => grid_player02.toString(player_02, boolean)
      }
    }
  }

  def checkGuess(playerInput: String, playerStatus: Boolean, grid: Grid): Boolean = {
    var hit = false
    val input = playerInput.split(" ")

    try {
      if (input.length == 2) { // schöner mit for <- schleife lösen
        val x = input(0).toInt
        val y = input(1).toInt

        grid.getValue(x, y) match {
          case 0 => grid.setField(x, y, 3)
          case 1 => {
            hit = true
            grid.setField(x, y, 2)
          }
          case _ =>
        }
      } else {
        print("Format Error\n")
        hit = true
      }
    } catch {
      case e: NumberFormatException => println("you have to input numbers\n")
        hit = true
    }

    if (hit) {
      playerStatus
    } else {
      !playerStatus
    }

  }

}
