package Battleship.TUI

import Battleship.controller.Controller
import Battleship.util.Observer

import scala.collection.mutable

class Tui(controller: Controller) extends Observer {

  var printGridOption = true
  var tui = new TUIInterface(controller)

  val gridPrint = false // grid will print without placed ships
  var playerStatus = true // true = player 1, false = player2
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


  def processLine(input: String): Unit = {
    if (shipProcess) shipProcess = false
    if (printGridOption) printGridOption = false

    input match {
      case "q" =>
      case "getPlayerconfig" => print(TUIMethods.printGetPlayer(controller.player_01, controller.player_02))
      case _ => // grid nur mit spiel makierungen ausgeben
        update
        if (playerStatus) playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player02)
        else playerStatus = controller.checkGuess(input, playerStatus, controller.grid_player01)
    }
  }

  override def update: Boolean = {
    if (shipProcess) {
      if (shipProcess) print(controller.gridToString(0, printGridOption))
      else print(controller.gridToString(1, printGridOption))
    }
    if (playerStatus) print(controller.gridToString(1, printGridOption))
    else print(controller.gridToString(0, printGridOption))
    true
  }
}
