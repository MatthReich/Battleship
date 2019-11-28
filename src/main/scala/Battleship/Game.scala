package Battleship

import Battleship.TUI.{TUIInterface, Tui}
import Battleship.controller.{Controller, GameStatus}
import Battleship.model.Grid

//noinspection ScalaStyle
object Game {

  val fieldSize = 10
  val controller = new Controller(Grid(fieldSize), Grid(fieldSize))
  val tui = new TUIInterface(controller)
  val tuii = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    tui.setPlayers()
    tui.playerConfiguration()

    var input: String = ""

    do {
      tuii.printGrid(0)
      tuii.printShipSetSettings(controller.nr)
      input = scala.io.StdIn.readLine().toString
      tuii.shipProcess(input, 0) // 0 = player1
    } while ((controller.nr(0) + controller.nr(1) + controller.nr(2) + controller.nr(3)) != 0)

    do {
      tuii.printGrid(1)
      tuii.printShipSetSettings(controller.nr2)
      input = scala.io.StdIn.readLine().toString
      tuii.shipProcess(input,1) // 1 = player2
    } while ((controller.nr2(0) + controller.nr2(1) + controller.nr2(2) + controller.nr2(3)) != 0)

    do {
      input = scala.io.StdIn.readLine().toString
      tuii.processLine(input)
      // @TODO Pattern GameStatus instead of winStatement
      if (controller.gameStatus == GameStatus.SOLVED) return
    } while (input != "q")

  }
}