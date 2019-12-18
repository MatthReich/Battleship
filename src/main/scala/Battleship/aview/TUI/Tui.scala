package Battleship.aview.TUI

import Battleship.controller._
import Battleship.model.shipComponent.InterfaceShip

import scala.collection.mutable
import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {

  listenTo(controller)

  reactions += {
    case event: CellChanged =>
    case event: PlayerChanged =>
  }

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

  def shipProcessLong(input: String): Unit = {
    input match {
      case "delete Ship" => controller.deleteShip()
      case _ => shipProcess(input)
    }
  }

  def shipProcess(input: String): Unit = {
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

  def decreaseShipNumbersToPlace(ship: InterfaceShip, boolean: Boolean, increase: Boolean): Unit = {
    if (boolean || increase) {
      val shipSize: Int = ship.getSize
      var x: Int = -1
      if (increase) {
        x = 1
      }
      shipSize match {
        case 2 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(0) += x
            case PlayerState.PLAYER_TWO => controller.nr2(0) += x
          }
        case 3 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(1) += x
            case PlayerState.PLAYER_TWO => controller.nr2(1) += x
          }
        case 4 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(2) += x
            case PlayerState.PLAYER_TWO => controller.nr2(2) += x
          }
        case 5 =>
          controller.playerState match {
            case PlayerState.PLAYER_ONE => controller.nr(3) += x
            case PlayerState.PLAYER_TWO => controller.nr2(3) += x
          }
      }
    }
  }

  def processLine(input: String): Unit = {

    input match {
      case "q" => System.exit(0)
      case "getPlayerConfig" => print(tui.printGetPlayer(controller.player_01, controller.player_02))
      case "getGameStatus" => print(controller.gameState + "\n")
      case "getPlayerStatus" => print(controller.playerState + "\n")
      case "admin: printGrid 1" => print(controller.grid_player01.toString(controller.player_01, true, controller.playerState))
      case "admin: printGrid 2" => print(controller.grid_player02.toString(controller.player_02, true, controller.playerState))
      case _ => {
        controller.gameState match {
          case GameState.PLAYERSETTING => {
            controller.setPlayers(input)
          }
          case GameState.SHIPSETTING => {
            controller.shipSet = false
            shipProcessLong(input)
            decreaseShipNumbersToPlace(controller.ship, controller.shipSet, controller.shipDelete)
          }
          case GameState.IDLE => input match {

            case "undo Guess" =>
              if (controller.playerState == PlayerState.PLAYER_ONE) {
                controller.undoGuess(input, controller.grid_player02)
              } else {
                controller.undoGuess(input, controller.grid_player01)
              }
              update
            case _ => // grid nur mit spiel makierungen ausgeben
              if (controller.playerState == PlayerState.PLAYER_ONE) {
                controller.checkGuess(input, controller.grid_player02)
                controller.setLastGuess(input)
              }
              else {
                controller.checkGuess(input, controller.grid_player01)
                controller.setLastGuess(input)
              }
              update
          }
          case GameState.SOLVED => System.exit(0)
        }
      }
    }
  }

  def update: Boolean = {
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
    }
    true
  }

}
