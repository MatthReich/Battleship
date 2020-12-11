package Battleship.controller

import Battleship.controller.ControllerBaseImpl.GameState.GameState
import Battleship.controller.ControllerBaseImpl.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip

import scala.swing.Publisher

trait InterfaceController extends Publisher {

  def init(): Unit

  def getCreator1: InterfacePerson

  def getCreator2: InterfacePerson

  def setPlayers(input: String): Unit

  def getPlayer1: InterfacePerson

  def getPlayer2: InterfacePerson

  def getGridPlayer1: InterfaceGrid

  def getGridPlayer2: InterfaceGrid

  def setWholeNrPlayer1(array: Array[Int]): Unit

  def setWholeNrPlayer2(array: Array[Int]): Unit

  def setNrPlayer1(int: Int, value: Int): Unit

  def setNrPlayer2(int: Int, value: Int): Unit

  def getNrPlayer1(): Array[Int]

  def getNrPlayer2(): Array[Int]

  def setPlayerState(playerState: PlayerState): Unit

  def getPlayerState: PlayerState

  def setGameState(gameState: GameState): Unit

  def getGameState: GameState

  def getShip: InterfaceShip

  def setShipDelete(boolean: Boolean): Unit

  def getShipDelete: Boolean

  def setShipSet(boolean: Boolean): Unit

  def getShipSet: Boolean

  def createShip(): Unit

  def setShips(): Unit

  def deleteShip(): Unit

  def checkShipSetting(playerInput: String): Boolean

  def setLastGuess(string: String): Unit

  def getLastGuess(): String

  def checkGuess(playerInput: String, grid: InterfaceGrid): Unit

  def undoGuess(playerInput: String, grid: InterfaceGrid): Unit

  def save(): Unit

  def load(): Unit

  def setShip(coordinates:String): Unit
}
