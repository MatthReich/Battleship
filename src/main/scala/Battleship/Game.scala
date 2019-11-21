package Battleship

import Battleship.TUI.TUIInterface
import Battleship.controller.Controller

object Game {

  val controller = new Controller()
  val tui = new TUIInterface(controller)

  def main(args: Array[String]): Unit = {

    tui.setPlayers()
    tui.playerConfiguration()

    do {
      tui.setShips(controller.player_01, controller.playerGrid_01, controller.nr)
    } while ((controller.nr(0) + controller.nr(1) + controller.nr(2) + controller.nr(3)) != 0)

    do {
      tui.setShips(controller.player_02, controller.playerGrid_02, controller.nr2)
    } while ((controller.nr2(0) + controller.nr2(1) + controller.nr2(2) + controller.nr2(3)) != 0)

    tui.processLine()
  }
}
