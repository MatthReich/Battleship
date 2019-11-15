package Battleship.TUI

import Battleship.controller.Controller
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

  def setShips(): Unit = {
  controller.addShipUpper(controller.player_01, controller.player_02, controller.playerGrid_01,
      controller.playerGrid_02, controller.nr, controller.nr2)
  }

  def processLine(input: String): Unit = {

  }

  override def update(): Unit = {

  }
}