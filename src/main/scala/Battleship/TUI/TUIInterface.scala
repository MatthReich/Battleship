package Battleship.TUI

import Battleship.TUI.TUIMethods._
import Battleship.controller.Controller
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

  override def update: Boolean = {
    true
  }
}
