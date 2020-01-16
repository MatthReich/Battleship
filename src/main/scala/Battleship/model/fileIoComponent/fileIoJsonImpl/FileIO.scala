package Battleship.model.fileIoComponent.fileIoJsonImpl

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.controller.{GameState, PlayerState}
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: (InterfaceGrid, InterfaceGrid, InterfacePerson, InterfacePerson, Array[Int], Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = {
    var grid1: InterfaceGrid = null
    var grid2: InterfaceGrid = null
    var player: InterfacePerson = null
    var player2: InterfacePerson = null
    var shipSetting: Array[Int] = null
    var shipSetting2: Array[Int] = null
    var ship: Ship = null
    var shipCoordsSetting: Array[Int] = null

    val source: String = Source.fromFile("saveFile.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid1" \ "size").get.toString.toInt
    grid1 = Grid()
    for (index <- 0 until size * size) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      val value = (json \\ "value") (index).as[Int]
      grid1.setField(row, col, value)
    }

    val shipSet: Boolean = (json \ "shipSet").get.toString.toBoolean
    val shipDelete: Boolean = (json \ "shipDelete").get.toString.toBoolean
    val lastGuess: String = (json \ "lastGuess").get.toString
    val gameState: GameState = (json \ "gameState") match {
      case GameState.PLAYERSETTING => GameState.PLAYERSETTING
      case GameState.SHIPSETTING => GameState.SHIPSETTING
      case GameState.IDLE => GameState.IDLE
      case GameState.SOLVED => GameState.SOLVED
    }
    val playerState: PlayerState = (json \ "playerState") match {
      case PlayerState.PLAYER_ONE => PlayerState.PLAYER_ONE
      case PlayerState.PLAYER_TWO => PlayerState.PLAYER_TWO
    }

    (grid1, grid2, player, player2, shipSetting, shipSetting2, ship, shipCoordsSetting, shipSet, shipDelete, lastGuess, gameState, playerState)
  }

  override def save(grid: InterfaceGrid, grid2: InterfaceGrid, player: InterfacePerson, player2: InterfacePerson, shipSetting: Array[Int], shipSetting2: Array[Int], ship: InterfaceShip, shipCoordsSetting: Array[Int], shipSet: Boolean, shipDelete: Boolean, lastGuess: String, gameState: GameState, playerState: PlayerState): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("safeFile.json"))
    pw.write(Json.prettyPrint(grid1ToJson(grid)))
    // pw.write(Json.prettyPrint(grid2ToJson(grid2)))
    pw.close()
  }

  implicit val player = new Writes[InterfacePerson] {
    def writes(player: InterfacePerson): JsValue = Json.obj(
      "player" -> Json.toJson(player.toString) // @TODO functionable?
    )
  }

  implicit val shipSettings = new Writes[Array[Int]] {
    def writes(array: Array[Int]): JsValue = Json.obj(
      "shipSetting" -> Json.toJson(array) // @TODO functionable?
    )
  }

  implicit val ship = new Writes[InterfaceShip] {
    override def writes(ship: InterfaceShip): JsValue = Json.obj(
      "ship" -> ship.getCoordinates // @TODO functionable?
    )
  }


  implicit val shipCoordsSetting = new Writes[Array[Int]] {
    override def writes(shipCoordsSetting: Array[Int]): JsValue = Json.obj(
      "shipCoordsSetting" -> Json.toJson(shipCoordsSetting)
    )
  }

  implicit val shipSet = new Writes[Boolean] {
    override def writes(shipSet: Boolean): JsValue = Json.obj(
      "shipSet" -> shipSet
    )
  }

  implicit val shipDelete = new Writes[Boolean] {
    override def writes(shipDelete: Boolean): JsValue = Json.obj(
      "shipDelete" -> shipDelete
    )
  }

  implicit val lastGuess = new Writes[String] {
    override def writes(lastGuess: String): JsValue = Json.obj(
      "shipDelete" -> lastGuess
    )
  }

  implicit val gameState = new Writes[GameState] {
    override def writes(gameState: GameState): JsValue = Json.obj(
      "gameState" -> gameState
    )
  }

  implicit val playerState = new Writes[PlayerState] {
    override def writes(playerState: PlayerState): JsValue = Json.obj(
      "playerState" -> playerState
    )
  }


  def grid1ToJson(grid: InterfaceGrid) = {
    val gridSize = 10
    Json.obj(
      "grid1" -> Json.obj(
        "size" -> JsNumber(gridSize),
        "cells" -> Json.toJson(
          for {
            row <- 0 until gridSize
            col <- 0 until gridSize
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "value" -> grid.getValue(row, col)
            )
          }
        )
      )
    )
  }

  def grid2ToJson(grid: InterfaceGrid) = {
    val gridSize = 10
    Json.obj(
      "grid2" -> Json.obj(
        "size" -> JsNumber(gridSize),
        "cells" -> Json.toJson(
          for {
            row <- 0 until gridSize
            col <- 0 until gridSize
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "value" -> Json.toJson(grid.getValue(row, col))
            )
          }
        )
      )
    )
  }

}
