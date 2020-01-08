package Battleship.aview.TUI

import Battleship.controller._
import Battleship.model.shipComponent.InterfaceShip

import scala.collection.mutable
import scala.swing.Reactor

class Tui(controller: InterfaceController) extends Reactor {

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

  def processLine(input: String): Unit = {

    input match {
      case "q" => System.exit(0)
      case "getPlayerConfig" => print(tui.printGetPlayer(controller.getPlayer1, controller.getPlayer2))
      case "getGameState" => print(controller.getGameState + "\n")
      case "gGS" => print(controller.getGameState + "\n")
      case "getPlayerState" => print(controller.getPlayerState + "\n")
      case "gPS" => print(controller.getPlayerState + "\n")
      case "admin: printGrid 1" => print(controller.getGridPlayer1.toString(controller.getPlayer1, true, controller.getPlayerState))
      case "admin: printGrid 2" => print(controller.getGridPlayer2.toString(controller.getPlayer2, true, controller.getPlayerState))
      case _ => {
        controller.getGameState match {
          case GameState.PLAYERSETTING => {
            controller.setPlayers(input)
          }
          case GameState.SHIPSETTING => {
            controller.shipSet(false)
            shipProcessLong(input)
            decreaseShipNumbersToPlace(controller.getShip, controller.getShipSet, controller.getShipDelete)
          }
          case GameState.IDLE => input match {

            case "undo Guess" =>
              if (controller.getPlayerState == PlayerState.PLAYER_ONE) {
                controller.undoGuess(input, controller.getGridPlayer2)
              } else {
                controller.undoGuess(input, controller.getGridPlayer2)
              }
              update
            case _ =>
              if (controller.getPlayerState == PlayerState.PLAYER_ONE) {
                controller.checkGuess(input, controller.getGridPlayer2)
                controller.setLastGuess(input)
              }
              else {
                controller.checkGuess(input, controller.getGridPlayer1)
                controller.setLastGuess(input)
              }
              update
          }
          case GameState.SOLVED => System.exit(0)
        }
      }
    }
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
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(0, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(0, x)
          }
        case 3 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(1, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(1, x)
          }
        case 4 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(2, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(2, x)
          }
        case 5 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(3, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(3, x)
          }
      }
    }
  }

  def update: Boolean = {
    if (shipProcess) {
      print(controller.gridToString(0, printGridOption))
    } else {
      if (controller.getPlayerState == PlayerState.PLAYER_ONE) {
        print(controller.gridToString(1, printGridOption))
      }
      else {
        print(controller.gridToString(0, printGridOption))
      }
      if (controller.getGridPlayer1.winStatement() || controller.getGridPlayer2.winStatement()) {
        controller.setGameState(GameState.SOLVED)
      }
    }
    true
  }

}
