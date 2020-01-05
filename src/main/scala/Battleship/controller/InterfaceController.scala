package Battleship.controller

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip

import scala.swing.Publisher

trait InterfaceController extends Publisher {

  def checkShipSetting(playerInput: String): Boolean

  def setPlayers(input: String): Unit

  def gridToString(int: Int, boolean: Boolean): String

  def shipToString(ship: InterfaceShip): String

  def deleteShip(): Unit

  def setShips(): Unit

  def createShip(): Unit

  def undoGuess(playerInput: String, grid: InterfaceGrid): Unit

  def setLastGuess(string: String): Unit

  def checkGuess(playerInput: String, grid: InterfaceGrid): Unit

  def getGridPlayer1: InterfaceGrid

  def getGridPlayer2: InterfaceGrid

  def getPlayerState: PlayerState

  def setPlayerState(playerState: PlayerState): Unit

  def setGameState(gameState: GameState): Unit

  def getCreator1: InterfacePerson

  def getCreator2: InterfacePerson

  def getPlayer1: InterfacePerson

  def getPlayer2: InterfacePerson

  def getGameState: GameState

  def shipSet(boolean: Boolean): Unit

  def getShipSet: Boolean

  def getShip: InterfaceShip

  def getShipDelete: Boolean

  def setNrPlayer1(int: Int, value: Int): Unit

  def setNrPlayer2(int: Int, value: Int): Unit

  def setWholeNrPlayer1(array: Array[Int]): Unit

  def setWholeNrPlayer2(array: Array[Int]): Unit

  def getLastGuess(): String

  def getNrPlayer1(): Array[Int]

  def getNrPlayer2(): Array[Int]

  def setgrid_player01(interfaceGrid: InterfaceGrid): Unit

  def setgrid_player02(interfaceGrid: InterfaceGrid): Unit

  def init(): Unit

  def setShipSet(boolean: Boolean): Unit

  def setShipDelete(boolean: Boolean): Unit
}
