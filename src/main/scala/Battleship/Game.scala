package Battleship

import Battleship.TUI.TUIInterface
import Battleship.controller.Controller

object Game {

  val controller = new Controller()
  val tui = new TUIInterface(controller)

  def main(args: Array[String]): Unit = {

    tui.setPlayers
    tui.playerConfiguration()
    tui.setShips()

  }
}
