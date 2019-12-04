package Battleship.aview

import Battleship.Game.{controller, tui}
import Battleship.controller.{Controller, GameState, PlayerState}
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.util.Observer

import scala.collection.mutable

class Tui(controller: Controller) extends Observer {

  var printGridOption = true
  val tui = new TUIInterface(controller)

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

  def shipProcess(input: String): Unit = { // player: 0 = player1, 1 = player2
    if (controller.checkShipSetting(input)) {
      controller.createShip()
      controller.setShips()
    }
  }

  def printFirstTimeProcessLine(): Unit = {
    if (shipProcess) shipProcess = false
    if (printGridOption) printGridOption = false

    update
  }

  def decreaseShipNumbersToPlace(ship: Ship, boolean: Boolean): Unit = {
    if (boolean) {
      val shipSize: Int = ship.getSize
      shipSize match {
        case 2 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(0) -= 1
            case PlayerState.PLAYER_TWO => controller.nr2(0) -= 1
          }
        case 3 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(1) -= 1
            case PlayerState.PLAYER_TWO => controller.nr2(1) -= 1
          }
        case 4 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(2) -= 1
            case PlayerState.PLAYER_TWO => controller.nr2(2) -= 1
          }
        case 5 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(3) -= 1
            case PlayerState.PLAYER_TWO => controller.nr2(3) -= 1
          }
      }
    }
  }



  def processLine(input: String): Unit = {

    input match {
      case "q" => // exit game
      case "getPlayerConfig" => print(tui.printGetPlayer(controller.player_01, controller.player_02))
      case "getGameStatus" => print(controller.gameState + "\n")
      case "getPlayerStatus" => print(controller.playerState + "\n")
      case _ => // grid nur mit spiel makierungen ausgeben
        if (controller.playerState == PlayerState.PLAYER_ONE) {
          controller.playerState = controller.checkGuess(input, controller.grid_player02)
        }
        else {
          controller.playerState = controller.checkGuess(input, controller.grid_player01)
        }
        update
    }
  }

  override def update: Boolean = {
    if (shipProcess) {
        print(controller.gridToString(0, printGridOption))
    }
    if (controller.playerState == PlayerState.PLAYER_ONE) {
      print(controller.gridToString(1, printGridOption))
    }
    else {
      print(controller.gridToString(0, printGridOption))
    }
    if (controller.grid_player01.winStatement() || controller.grid_player02.winStatement()) {
      controller.gameState = GameState.SOLVED
    } else {
      controller.gameState = GameState.IDLE
    }
    true
  }

}
