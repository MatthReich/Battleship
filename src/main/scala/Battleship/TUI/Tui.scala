package Battleship.TUI

import Battleship.controller.Controller
import Battleship.util.Observer

class Tui(controller: Controller) extends Observer {

  var printGridOption: Boolean = false
  var tui = new TUIInterface(controller)

  val gridPrint = false // grid will print without placed ships
  var playerStatus = true // true = player 1, false = player2
  var firstTime = true

  def processLine(input: String): Unit = {

    input match {
      case "q" =>
      case _ => // grid nur mit spiel makierungen ausgeben
        update
        if (playerStatus) playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player02)
        else playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player01)
    }
  }

  override def update: Boolean = {
    if (playerStatus) print(controller.gridToString(1, printGridOption))
    else print(controller.gridToString(0, printGridOption))
    true
  }
}
