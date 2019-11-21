package Battleship.TUI

import Battleship.TUI.TUIMethods._
import Battleship.TUI.TUIProcessLine._
import Battleship.controller.Controller
import Battleship.model.{Grid, Player}
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

  def setShipsPrint(player: Player, grid: Grid, nr: Array[Int]): Unit = {
    output(printGrid(grid, player, true))
    output(printNrOfShips(nr))
  }

  def setShips(player: Player, grid: Grid, nr: Array[Int]): Unit = {
    setShipsPrint(player, grid, nr)
    val ship: String = input()
    val check = checkValidShip(ship)
    if (check(0) != 10) {
      controller.addShips(grid, check, nr)
    }
  }

  def processLine(): Unit = {
    var playerStatus = true   // true = player 1, false = player2
    var playerInput: String = ""
    var firstTime = true
    val gridPrint = false // grid will print without placed ships
    var winStatement = false

    do {
      if (firstTime) {
        output(printGrid(controller.playerGrid_02, controller.player_01, gridPrint))
        firstTime = false
      } else {  // grid nur mit spiel makierungen ausgeben
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

      winStatement = controller.playerGrid_01.winStatement()
      if (!winStatement) controller.playerGrid_02.winStatement()
      if (winStatement) return

    } while (playerInput != "q")
  }


  override def update(): Boolean = {
    true
  }
}