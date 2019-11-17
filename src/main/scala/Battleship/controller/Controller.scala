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
  var nr: Array[Int] = Array[Int](2, 0, 0, 0)
  var nr2: Array[Int] = Array[Int](2, 0, 0, 0)


  def addShips(grid: Grid, ship: Array[Int], nrArray: Array[Int]): Unit = {
    grid.setShip(ship, nrArray)
    notifyObservers()
  }
}
