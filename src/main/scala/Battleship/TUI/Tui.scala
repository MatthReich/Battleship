package Battleship.TUI

import Battleship.controller.Controller
import Battleship.util.Observer

class Tui(controller: Controller) extends Observer {

  var boolean: Boolean = false
  var tui = new TUIInterface(controller)

  val gridPrint = false // grid will print without placed ships
  var playerStatus = true // true = player 1, false = player2
  var firstTime = true

  def processLine(input: String): Unit = {

    update

    input match {
      case "q" =>
      case _ => // grid nur mit spiel makierungen ausgeben
        if (playerStatus) playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player02)
        else playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player01)
    }
  }

  override def update: Boolean = {
    if (boolean) {
      print(controller.gridToString(0, boolean))
      print(controller.gridToString(1, boolean))
    } else {
      print("podhfpishdfpisdpfshpdfdypfpsidhfnoi \n")
      if (playerStatus) print(controller.gridToString(1, boolean))
      else print(controller.gridToString(0, boolean))
    }
    true
  }
}

