package Battleship.TUI

import Battleship.TUI.TUIMethods._
import Battleship.controller.Controller
import Battleship.model.{Grid, Player}
import Battleship.util.Observer


class TUIInterface(controller: Controller) extends Observer {


  def setPlayers(): Unit = {
    controller.add(this)
    output(printWelcome(controller.creator_01, controller.creator_02))
    output(printSetPlayer(1))
    controller.player_01 = setPlayer(1)
    output(printSetPlayer(2))
    controller.player_02 = setPlayer(2)
  }

  def playerConfiguration(): Unit = {
    output(printGetPlayer(controller.player_01, controller.player_02))
  }

  def setShipsPrint(player: Player, grid: Grid, nr: Array[Int]): Unit = {
    output(printGrid(grid, player))
    output(printNrOfShips(nr))
  }

  def setShips(input: String, grid: Grid, nr: Array[Int]): Unit = {
    val tmp = controller.addShips(input)
    if (tmp(0) != 10) {
      grid.setShip(tmp, nr)
    }
  }

  override def update(): Unit = {

  }
}