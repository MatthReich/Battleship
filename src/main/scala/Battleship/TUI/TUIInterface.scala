package Battleship.TUI

import Battleship.TUI.TUIMethods._
import Battleship.controller.Controller
import Battleship.model.{Grid, Player}
import Battleship.util.Observer


class TUIInterface(controller: Controller) extends Observer {

  controller.add(this)

  def setPlayers(): Unit = {
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

  def setShips(player: Player, grid: Grid, nr: Array[Int]): Unit = {
    setShipsPrint(player, grid, nr)
    val ship: String = input()
    val check = checkValidShip(ship)
    if (check(0) != 10) {
      controller.addShips(grid, check, nr)
    }
  }

  override def update(): Boolean = {
    true
  }
}