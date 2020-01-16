package Battleship.controller

import Battleship.GameModule
import Battleship.controller.GameState.{GameState, _}
import Battleship.controller.PlayerState.{PlayerState, _}
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.util.UndoManager
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._

import scala.swing.Publisher
import scala.util.{Failure, Success, Try}


class Controller @Inject() extends InterfaceController with Publisher {
  val injector = Guice.createInjector(new GameModule)
  val fileIo_Player01 = injector.instance[FileIOInterface]
  val fileIo_Player02 = injector.instance[FileIOInterface]
  val creator_02: InterfacePerson = injector.instance[InterfacePerson]
  var grid_player02: InterfaceGrid = injector.instance[InterfaceGrid]
  var grid_player01: InterfaceGrid = injector.instance[InterfaceGrid]
  var creator_01: InterfacePerson = injector.instance[InterfacePerson]
  var player_01: InterfacePerson = injector.instance[InterfacePerson]
  var player_02: InterfacePerson = injector.instance[InterfacePerson]
  var nr: Array[Int] = Array[Int](2, 0, 0, 0)
  var nr2: Array[Int] = Array[Int](2, 0, 0, 0)
  var ship: InterfaceShip = injector.getInstance(classOf[InterfaceShip]) //Ship(Array(0, 0, 0, 0), new StrategyCollideNormal)
  var shipCoordsSetting: Array[Int] = Array(0, 0, 0, 0)
  var shipSet: Boolean = false
  var shipDelete: Boolean = false
  var lastGuess: String = ""

  var gameState: GameState = PLAYERSETTING
  var playerState: PlayerState = PLAYER_ONE
  private val undoManager = new UndoManager

  override def init(): Unit = {
    creator_01.addName("Marcel Gaiser")
    creator_02.addName("Matthias Reichenbach")
  }

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
    ship = injector.getInstance(classOf[InterfaceShip])
    ship.setCoordinates(shipCoordsSetting)
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
    if (input != "") {
      if (playerState == PLAYER_ONE) {
        player_01.addName(input)
      } else if (playerState == PLAYER_TWO) {
        player_02.addName(input)
      }
    } else {
      if (playerState == PLAYER_ONE) {
        player_01.addName("player_0" + 1)
      } else if (playerState == PLAYER_TWO) {
        player_02.addName("player_0" + 2)
      }
    }
    if (playerState == PLAYER_ONE) {
      playerState = PLAYER_TWO
    } else if (playerState == PLAYER_TWO) {
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

  override def getNrPlayer1(): Array[Int] = nr

  override def getNrPlayer2(): Array[Int] = nr2

  override def setPlayerState(playerState: PlayerState): Unit = this.playerState = playerState

  override def setgrid_player01(interfaceGrid: InterfaceGrid): Unit = grid_player01 = interfaceGrid

  override def setgrid_player02(interfaceGrid: InterfaceGrid): Unit = grid_player02 = interfaceGrid

  override def getLastGuess(): String = lastGuess

  override def setShipSet(boolean: Boolean): Unit = shipSet = boolean

  override def setShipDelete(boolean: Boolean): Unit = shipDelete = boolean

  override def setWholeNrPlayer1(array: Array[Int]): Unit = nr = array

  override def setWholeNrPlayer2(array: Array[Int]): Unit = nr2 = array

  override def load(): Unit = {
    val values: (InterfaceGrid, InterfaceGrid, InterfacePerson, InterfacePerson, Array[Int], Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = fileIo_Player01.load
    grid_player01 = values._1
    grid_player02 = values._2
    player_01 = values._3
    player_02 = values._4
    nr = values._5
    nr2 = values._6
    ship = values._7
    shipCoordsSetting = values._8
    shipSet = values._9
    shipDelete = values._10
    lastGuess = values._11
    gameState = values._12
    playerState = values._13

    publish(new CellChanged)
  }

  override def save(): Unit = {

    publish(new CellChanged)
  }
}
