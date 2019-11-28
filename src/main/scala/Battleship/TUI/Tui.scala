package Battleship.TUI

import Battleship.controller.Controller
import Battleship.util.Observer

class Tui(controller: Controller) extends Observer {

  var boolean: Boolean = true
  var tui = new TUIInterface(controller)

  def provcessLine(input: String): Unit = {

    var playerStatus = true // true = player 1, false = player2
    var playerInput: String = ""
    var firstTime = true
    val gridPrint = false // grid will print without placed ships

    do {
      if (firstTime) {
        print(controller.gridToString(1, gridPrint))
        firstTime = false
      } else { // grid nur mit spiel makierungen ausgeben
        if (playerStatus) print(controller.gridToString(1, gridPrint))
        else print(controller.gridToString(0, gridPrint))
      }

      playerInput = scala.io.StdIn.readLine().toString

      playerInput match {
        case "q" =>
        case _ => // grid nur mit spiel makierungen ausgeben
          if (playerStatus) playerStatus = TUIProcessLine.processLineIntern(playerStatus, playerInput, controller.grid_player02)
          else playerStatus = TUIProcessLine.processLineIntern(playerStatus, playerInput, controller.grid_player01)
      }

      if (controller.grid_player01.winStatement()) return
      if (controller.grid_player02.winStatement()) return

    } while (playerInput != "q")
  }

  override def update: Boolean = {
    if (boolean) {
      print(controller.gridToString(0, boolean))
      print(controller.gridToString(1, boolean))
    } else {
      print(controller.gridToString(0, boolean))
    }
    true
  }
}
