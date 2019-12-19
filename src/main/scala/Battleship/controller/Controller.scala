package Battleship.controller

import Battleship.controller.GameState._
import Battleship.controller.PlayerState._
import Battleship.model.Person.{Creator, InterfacePerson, Player}
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import Battleship.util.UndoManager

import scala.util.{Failure, Success, Try}


class Controller(var grid_player01: InterfaceGrid, var grid_player02: InterfaceGrid) extends InterfaceController {

  val creator_02: InterfacePerson = Creator("Matthias Reichenbach")
  var creator_01: InterfacePerson = Creator("Marcel Gaiser")
  var player_01: InterfacePerson = Player("")
  var player_02: InterfacePerson = Player("")
  var nr: Array[Int] = Array[Int](2, 0, 0, 0)
  var nr2: Array[Int] = Array[Int](1, 0, 0, 0)
  var ship: InterfaceShip = Ship(Array(0, 0, 0, 0), new StrategyCollideNormal)
  var shipCoordsSetting: Array[Int] = Array(0, 0, 0, 0)
  var shipSet: Boolean = false
  var shipDelete: Boolean = false
  var lastGuess: String = ""

  var gameState: GameState = PLAYERSETTING
  var playerState: PlayerState = PLAYER_ONE
  private val undoManager = new UndoManager

  override def checkShipSetting(playerInput: String): Boolean = {
    // @TODO look if its functionable
    var functionable: Boolean = true
    val myString = playerInput.split(" ")

    val convertDoubles = myString.map { x =>
      Try(x.toInt)
    }

    val convertedArray = convertDoubles.map {
      case Success(res) => res
      case Failure(f) => None
    }

    for (x <- convertedArray) {
      if (x == None) {
        functionable = false
        print("wrong input")
      }
    }

    if (functionable) {
      playerInput.split("\n").map { entry =>
        val input = entry.split(" ")
        if (input.length == 4) {

          shipCoordsSetting(0) = input(0).toInt
          shipCoordsSetting(1) = input(1).toInt
          shipCoordsSetting(2) = input(2).toInt
          shipCoordsSetting(3) = input(3).toInt

          if ((shipCoordsSetting(0) == shipCoordsSetting(2) && shipCoordsSetting(1) == shipCoordsSetting(3))
            || (!(shipCoordsSetting(0) == shipCoordsSetting(2)) && !(shipCoordsSetting(1) == shipCoordsSetting(3)))) {
            print("too short ship length")
            return false
          }
          playerState match {
            case PLAYER_ONE => if (nr(getSize() - 2) > 0) return true
            case PLAYER_TWO => if (nr2(getSize() - 2) > 0) return true
          }
        } else {
          print("Format Error\n")
        }
        true
      }
    }
    false
  }

  private def getSize(): Int = {
    if (shipCoordsSetting(0) == shipCoordsSetting(2)) {
      val s = shipCoordsSetting(3) - shipCoordsSetting(1) + 1
      if (s > 0) {
        s
      }
      else {
        val s = shipCoordsSetting(1) - shipCoordsSetting(3) + 1
        s
      }
    } else {
      val s = shipCoordsSetting(2) - shipCoordsSetting(0) + 1
      if (s > 0) {
        s
      }
      else {
        val s = shipCoordsSetting(0) - shipCoordsSetting(2) + 1
        s
      }
    }
  }

  override def checkGuess(playerInput: String, grid: InterfaceGrid): Unit = {
    undoManager.setValue(new ProcessCommand(playerInput, grid, playerState, this))
    publish(new CellChanged)
  }

  override def setLastGuess(string: String): Unit = {
    lastGuess = string
  }

  override def undoGuess(playerInput: String, grid: InterfaceGrid): Unit = {
    undoManager.undoStep(new ProcessCommand(lastGuess, grid, playerState, this))
    publish(new CellChanged)
  }

  override def createShip(): Unit = {
    shipDelete = false
    ship = Ship(shipCoordsSetting, new StrategyCollideNormal)
    publish(new CellChanged)
  }

  override def setShips(): Unit = {
    undoManager.setValue(new SetCommand(playerState, shipCoordsSetting, this))
    publish(new CellChanged)
  }

  override def deleteShip(): Unit = {
    undoManager.undoStep(new SetCommand(playerState, shipCoordsSetting, this))
    publish(new CellChanged)
  }

  override def shipToString(ship: InterfaceShip): String = {
    ship.toString
  }

  override def gridToString(int: Int, boolean: Boolean): String = {
    int match {
      case 0 =>
        if (boolean) {
          grid_player01.toString(player_01, boolean, playerState)
        } else {
          grid_player01.toString(player_01, boolean, playerState)
        }
      case 1 =>
        if (boolean) {
          grid_player02.toString(player_02, boolean, playerState)
        } else {
          grid_player02.toString(player_02, boolean, playerState)
        }
    }
  }

  override def setPlayers(input: String): Unit = {
    var player: Player = Player(" ")
    if (input != "") {
      player = Player(input)
    } else {
      if (playerState == PLAYER_ONE) {
        player = Player("player_0" + 1)
      } else if (playerState == PLAYER_TWO) {
        player = Player("player_0" + 2)
      }
    }
    if (playerState == PLAYER_ONE) {
      player_01 = player
      playerState = PLAYER_TWO
    } else if (playerState == PLAYER_TWO) {
      player_02 = player
      playerState = PLAYER_ONE
      gameState = SHIPSETTING
      publish(new PlayerChanged)
    }
  }

  override def getGridPlayer1: InterfaceGrid = grid_player01

  override def getGridPlayer2: InterfaceGrid = grid_player02

  override def getPlayerState: PlayerState = playerState

  override def getCreator1: InterfacePerson = creator_01

  override def getCreator2: InterfacePerson = creator_02

  override def getPlayer1: InterfacePerson = player_01

  override def getPlayer2: InterfacePerson = player_02

  override def getGameState: GameState = gameState

  override def setGameState(gameState: GameState): Unit = this.gameState = gameState

  override def shipSet(boolean: Boolean): Unit = shipSet = boolean

  override def getShipSet: Boolean = shipSet

  override def getShip: InterfaceShip = ship

  override def getShipDelete: Boolean = shipDelete

  override def setNrPlayer1(int: Int, value: Int): Unit = nr(int) += value

  override def setNrPlayer2(int: Int, value: Int): Unit = nr2(int) += value
}
