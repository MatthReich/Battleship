package Battleship

import Battleship.TUI.TUIInterface
import Battleship.controller.Controller
import Battleship.model.Grid

object Game {

  val fieldSize = 10
  val controller = new Controller(Grid(fieldSize), Grid(fieldSize))
  val tui = new TUIInterface(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {

    tui.setPlayers()
    tui.playerConfiguration()

    do {
      tui.setShips(controller.player_01, controller.grid_player01, controller.nr)
    } while ((controller.nr(0) + controller.nr(1) + controller.nr(2) + controller.nr(3)) != 0)

    do {
      tui.setShips(controller.player_02, controller.grid_player02, controller.nr2)
    } while ((controller.nr2(0) + controller.nr2(1) + controller.nr2(2) + controller.nr2(3)) != 0)

    var playerStatus = true // true = player 1, false = player2
    var playerInput: String = ""
    var firstTime = true
    val gridPrint = false // grid will print without placed ships

    do {
      if (firstTime) {
        output(printGrid(controller.playerGrid_02, controller.player_01, gridPrint))
        println(controller.gridToString(1, gridPrint))
        firstTime = false
      } else { // grid nur mit spiel makierungen ausgeben
        if (playerStatus) output(printGrid(controller.playerGrid_02, controller.player_01, gridPrint))
        else output(printGrid(controller.playerGrid_01, controller.player_02, gridPrint))
      }

      playerInput = input()
      playerInput match {
        case "q" => " "
        // cases to get some more functions like getField again or so

        case _ => // grid nur mit spiel makierungen ausgeben
          if (playerStatus) playerStatus = processLineIntern(playerStatus, playerInput, controller.playerGrid_02)
          else playerStatus = processLineIntern(playerStatus, playerInput, controller.playerGrid_01)
      }

      if (controller.playerGrid_01.winStatement()) return
      if (controller.playerGrid_02.winStatement()) return

    } while (playerInput != "q")

  }
}


}