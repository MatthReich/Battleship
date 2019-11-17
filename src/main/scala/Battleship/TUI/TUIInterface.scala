package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.{Grid, Player}
import Battleship.util.Observer


class TUIInterface(controller: Controller) extends Observer {


  def setPlayers(): Unit = {
    controller.add(this)

    print(TUIMethods.printWelcome(controller.creator_01, controller.creator_02))

    controller.player_01 = TUIMethods.setPlayer(1)
    controller.player_02 = TUIMethods.setPlayer(2)

  }

  def playerConfiguration(): Unit = {
    print(TUIMethods.printGetPlayer(controller.player_01, controller.player_02))
  }

  def setShipsPrint(player: Player, grid: Grid, nr: Array[Int]): Unit = {
    print(TUIMethods.printGrid(grid, player))
    print(TUIMethods.printNrOfShips(nr))
  }

  def setShips(input: String, grid: Grid, nr: Array[Int]): Unit = {
    val tmp = controller.addShips(input)
    if (tmp(0) != 10) {
      grid.setShip(tmp, nr)
    }
  }

  def processLine(input: String): Unit = {

  }

  override def update(): Unit = {

  }
}