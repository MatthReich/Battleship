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

  def gridToString(int: Int, boolean: Boolean): String = int match {
    case 0 => grid_player01.toString(player_01, boolean)
    case 1 => grid_player02.toString(player_02, boolean)
  }

}
