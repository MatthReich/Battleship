package Battleship

import Battleship.TUI.{TUIInterface, Tui}
import Battleship.controller.{Controller, GameStatus, PlayerStatus}
import Battleship.model.Grid


object Game {

  val fieldSize = 10
  val controller = new Controller(Grid(fieldSize), Grid(fieldSize))
  val tui = new Tui(controller)
  val tuii = new TUIInterface(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {

    var input: String = ""

    tuii.printWelcomeX()
    print(tuii.printSetPlayer(1))
    input = scala.io.StdIn.readLine().toString
    tuii.setPlayers(input, 1)
    print(tuii.printSetPlayer(2))
    input = scala.io.StdIn.readLine().toString
    tuii.setPlayers(input, 2)
    tuii.playerConfiguration()

    do {
      tui.printGrid(0)
      tui.printShipSetSettings(controller.nr)
      input = scala.io.StdIn.readLine().toString
      tui.shipProcess(input, 0) // 0 = player1
    } while ((controller.nr(0) + controller.nr(1) + controller.nr(2) + controller.nr(3)) != 0)

    controller.playerStatus = PlayerStatus.PLAYER_TWO
    do {
      tui.printGrid(1)
      tui.printShipSetSettings(controller.nr2)
      input = scala.io.StdIn.readLine().toString
      tui.shipProcess(input, 1) // 1 = player2
    } while ((controller.nr2(0) + controller.nr2(1) + controller.nr2(2) + controller.nr2(3)) != 0)

    controller.playerStatus = PlayerStatus.PLAYER_ONE
    tui.printFirstTimeProcessLine()
    do {
      input = scala.io.StdIn.readLine().toString
      tui.processLine(input)
      if (controller.gameStatus == GameStatus.SOLVED) input = "q"
    } while (input != "q")

  }
}