package Battleship.TUI

import Battleship.controller.{Controller, GameStatus, PlayerStatus}
import Battleship.util.Observer

import scala.collection.mutable

class Tui(controller: Controller) extends Observer {

  var printGridOption = true
  var tui = new TUIInterface(controller)

  val gridPrint = false // grid will print without placed ships
  var firstTime = true
  var shipProcess = true



  def printGrid(int: Int): Unit = {
    print(controller.gridToString(int, printGridOption))
  }

  def printShipSetSettings(nr: Array[Int]): Unit = {
    val string = new mutable.StringBuilder("")
    string ++= "Please set your Ships:\n"
    var idx = 2
    for (tmp <- nr) {
      string ++= ("You can still place: " + Console.GREEN + tmp + "x " + Console.RESET + " " + idx + " Block Ship\n")
      idx += 1
    }
    string.toString()
    print(string)
  }

  def shipProcess(input: String, player: Int): Unit = { // player: 0 = player1, 1 = player2
    val ship = controller.checkShipSetting(input)
    controller.addShips(player, ship)
  }

  def printFirstTimeProcessLine(): Unit = {
    if (shipProcess) shipProcess = false
    if (printGridOption) printGridOption = false

    update
  }


  def processLine(input: String): Unit = {

    input match {
      case "q" => // exit game
      case "getPlayerConfig" => print(TUIMethods.printGetPlayer(controller.player_01, controller.player_02))
      case "getGameStatus" => print(controller.gameStatus + "\n")
      case "getPlayerStatus" => print(controller.playerStatus + "\n")
      case _ => // grid nur mit spiel makierungen ausgeben
        if (controller.playerStatus == PlayerStatus.PLAYER_ONE) {
          controller.playerStatus = controller.checkGuess(input, controller.grid_player02)
        }
        else {
          controller.playerStatus = controller.checkGuess(input, controller.grid_player01)
        }
        update
    }
  }

  override def update: Boolean = {
    if (shipProcess) {
      if (shipProcess) {
        print(controller.gridToString(0, printGridOption))
      }
      else {
        print(controller.gridToString(1, printGridOption))
      }
    }
    if (controller.playerStatus == PlayerStatus.PLAYER_ONE) {
      print(controller.gridToString(1, printGridOption))
    }
    else {
      print(controller.gridToString(0, printGridOption))
    }
    if (controller.grid_player01.winStatement() || controller.grid_player02.winStatement()) {
      controller.gameStatus = GameStatus.SOLVED
    } else {
      controller.gameStatus = GameStatus.IDLE
    }
    true
  }

}
