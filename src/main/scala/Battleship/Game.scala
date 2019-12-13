package Battleship

import Battleship.aview.GUI.startGUI
import Battleship.aview.TUI.{TUIInterface, Tui}
import Battleship.controller._
import Battleship.model.gridComponent.advancedGrid.Grid


object Game {

  val fieldSize = 10
  val controller = new Controller(Grid(fieldSize), Grid(fieldSize))
  val tui = new Tui(controller)
  val tuii = new TUIInterface(controller)
  val gui = new startGUI(controller)

  def main(args: Array[String]): Unit = {

    gui.visible = true

    var input: String = ""

    tuii.printWelcomeX()
/*

without print functionable
    print(tuii.printSetPlayer(1))
    print(tuii.printSetPlayer(2))

    tuii.playerConfiguration()

    do {

      tui.printGrid(0)
      tui.printShipSetSettings(controller.nr)

    }

    controller.playerState = PlayerState.PLAYER_TWO
    do {
      tui.printGrid(1)
      tui.printShipSetSettings(controller.nr2)
    }

    tui.printFirstTimeProcessLine()
*/
    do {


      input = scala.io.StdIn.readLine().toString
      tui.processLine(input)

      if ((controller.nr(0) + controller.nr(1) + controller.nr(2) + controller.nr(3)) == 0) {
        controller.playerState = PlayerState.PLAYER_TWO
      }
      if (controller.nr2(0) + controller.nr2(1) + controller.nr2(2) + controller.nr2(3) == 0) {
        controller.playerState = PlayerState.PLAYER_ONE
        controller.gameState = GameState.IDLE
      }
      if (controller.gameState == GameState.SOLVED) input = "q"
    } while (input != "q")

  }
}