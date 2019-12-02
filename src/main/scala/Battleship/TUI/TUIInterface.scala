package Battleship.TUI

import Battleship.TUI.TUIMethods._
import Battleship.controller.Controller
import Battleship.util.Observer


class TUIInterface(controller: Controller) extends Observer {

  controller.add(this)

  def setPlayers(): Unit = {
    print(printWelcome(controller.creator_01, controller.creator_02))
    print(printSetPlayer(1))
    controller.player_01 = setPlayer(1)
    print(printSetPlayer(2))
    controller.player_02 = setPlayer(2)
  }

  def playerConfiguration(): Unit = {
    print(printGetPlayer(controller.player_01, controller.player_02))
  }

  override def update: Boolean = {
    true
  }
}
