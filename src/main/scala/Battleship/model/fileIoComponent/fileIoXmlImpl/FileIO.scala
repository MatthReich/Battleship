package Battleship.model.fileIoComponent.fileIoXmlImpl

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.controller.{GameState, PlayerState}
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import com.google.inject.Inject

class FileIO @Inject()(var player: InterfacePerson, var player2: InterfacePerson, var grid01: InterfaceGrid, var grid02: InterfaceGrid, ship: InterfaceShip
                      ) extends FileIOInterface {

  override def load: (InterfaceGrid, InterfaceGrid, InterfacePerson, InterfacePerson, Array[Int], Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = {
    val file = scala.xml.XML.loadFile("saveFile.xml")
    var cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: Int = (cell \ "@valueY").text.toInt
      grid01.setField(row, col, value)
    }
    cellNodes = (file \\ "cellX")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@rowX").text.toInt
      val col: Int = (cell \ "@colX").text.toInt
      val value: Int = (cell \ "@valueX").text.toInt
      grid02.setField(row, col, value)
    }

    player.addName((file \\ "player1").text.toString)
    player2.addName((file \\ "player2").text.toString)

    val shipSet: Boolean = (file \\ "shipSet").text.toBoolean
    val shipDelete: Boolean = (file \\ "shipDelete").text.toBoolean
    val lastGuess: String = (file \\ "lastGuess").text.toString

    val shipSetting: Array[Int] = (file \\ "shipSetting").text.toArray[Int]
    val shipSetting2: Array[Int] = (file \\ "shipSetting2").text.toArray[Int]
    val shipCoordsSetting: Array[Int] = (file \\ "shipCoordsSet").text.toArray[Int]

    val gameState: GameState = (file \\ "gameState").text.toString match {
      case "PLAYERSETTING" => GameState.PLAYERSETTING
      case "SHIPSETTING" => GameState.SHIPSETTING
      case "IDLE" => GameState.IDLE
      case "SOLVED" => GameState.SOLVED
    }
    val playerState: PlayerState = (file \\ "playerState").text.toString match {
      case "PLAYER_ONE" => PlayerState.PLAYER_ONE
      case "PLAYER_TWO" => PlayerState.PLAYER_TWO
    }

    (grid01, grid02, player, player2, shipSetting, shipSetting2, ship, shipCoordsSetting, shipSet, shipDelete, lastGuess, gameState, playerState)
  }

  override def save(grid1: InterfaceGrid, grid2: InterfaceGrid, player: InterfacePerson, player2: InterfacePerson, shipSetting: Array[Int], shipSetting2: Array[Int], ship: InterfaceShip, shipCoordsSetting: Array[Int], shipSet: Boolean, shipDelete: Boolean, lastGuess: String, gameState: GameState, playerState: PlayerState): Unit = ???
}
