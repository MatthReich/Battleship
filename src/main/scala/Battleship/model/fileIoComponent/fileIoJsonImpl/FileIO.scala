package Battleship.model.fileIoComponent.fileIoJsonImpl

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.controller.{GameState, PlayerState}
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: (InterfaceGrid, InterfacePerson, Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = {
    var grid: InterfaceGrid = null
    var player: InterfacePerson = null
    var shipSetting: Array[Int] = null
    var ship: Ship = null
    var shipCoordsSetting: Array[Int] = null

    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString.toInt

    for (index <- 0 until size * size) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      val cell = (json \\ "cell") (index)
      val value = (cell \ "value").as[Int]
      // grid = grid.setField(row, col, value)
      val given = (cell \ "given").as[Boolean]
      val showCandidates = (cell \ "showCandidates").as[Boolean]
      // if (given) grid = grid.setGiven(row, col, value)
      // if (showCandidates) grid = grid.setShowCandidates(row, col)
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

    (grid, player, shipSetting, ship, shipCoordsSetting, shipSet, shipDelete, lastGuess, gameState, playerState)
  }

  override def save(grid: InterfaceGrid): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close
  }

  implicit val playerWrites = new Writes[InterfacePerson] {
    override def writes(player: InterfacePerson): JsValue = Json.obj(
      "player" -> player.toString
    )
  }

  implicit val shipSettings = new Writes[Array[Int]] {
    override def writes(array: Array[Int]): JsValue = Json.obj(
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


  def gridToJson(grid: InterfaceGrid) = {
    val gridSize = 10
    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(gridSize),
        "cells" -> Json.toJson(
          for {
            row <- 0 until gridSize
            col <- 0 until gridSize
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(grid.getValue(row, col))
            )
          }
        )
      )
    )
  }

}
