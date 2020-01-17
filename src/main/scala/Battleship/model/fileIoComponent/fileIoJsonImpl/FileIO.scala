package Battleship.model.fileIoComponent.fileIoJsonImpl

import Battleship.GameModule
import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.controller.{GameState, PlayerState}
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

import scala.io.Source

class FileIO @Inject extends FileIOInterface {
  val injector = Guice.createInjector(new GameModule)


  override def load: (InterfaceGrid, InterfaceGrid, InterfacePerson, InterfacePerson, Array[Int], Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = {
    var grid1: InterfaceGrid = null
    var grid2: InterfaceGrid = null

    var shipSetting: Array[Int] = null
    var shipSetting2: Array[Int] = null
    var ship: Ship = null
    var shipCoordsSetting: Array[Int] = null

    val source: String = Source.fromFile("saveFile.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = 10 // (json \ "grid1" \ "size").get.toString.toInt
    grid1 = Grid()

    for (index <- 0 until size * size) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      val value = (json \\ "value") (index).as[Int]
      grid1.setField(row, col, value)
    }
    // setzt grid 2 komplett 0
    grid2 = Grid()
    for (index <- 0 until size * size) {
      val row = (json \\ "rowX") (index).as[Int]
      val col = (json \\ "colX") (index).as[Int]
      val value = (json \\ "valueX") (index).as[Int]
      grid1.setField(row, col, value)
    }

    var playerString: String = (json \\ "player1").head.as[String]
    val player: InterfacePerson = injector.instance[InterfacePerson]
    player.addName(playerString)
    playerString = (json \\ "player2").head.as[String]
    val player2: InterfacePerson = injector.instance[InterfacePerson]
    player2.addName(playerString)


    val shipSet: Boolean = (json \\ "shipSet").head.as[Boolean]
    val shipDelete: Boolean = (json \\ "shipDelete").head.as[Boolean]
    val lastGuess: String = (json \\ "lastGuess").head.as[String]
    /*
        val gameState: GameState = (json \\ "gameState") match {
          case GameState.PLAYERSETTING => GameState.PLAYERSETTING
          case GameState.SHIPSETTING => GameState.SHIPSETTING
          case GameState.IDLE => GameState.IDLE
          case GameState.SOLVED => GameState.SOLVED
        }
        val playerState: PlayerState = (json \ "playerState") match {
          case PlayerState.PLAYER_ONE => PlayerState.PLAYER_ONE
          case PlayerState.PLAYER_TWO => PlayerState.PLAYER_TWO
        } */
    val gameState = GameState.IDLE
    val playerState = PlayerState.PLAYER_ONE

    (grid1, grid2, player, player2, shipSetting, shipSetting2, ship, shipCoordsSetting, shipSet, shipDelete, lastGuess, gameState, playerState)
  }

  override def save(grid: InterfaceGrid, grid2: InterfaceGrid, player: InterfacePerson, player2: InterfacePerson, shipSetting: Array[Int], shipSetting2: Array[Int], ship: InterfaceShip, shipCoordsSetting: Array[Int], shipSet: Boolean, shipDelete: Boolean, lastGuess: String, gameState: GameState, playerState: PlayerState): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("saveFile.json"))
    pw.write(Json.prettyPrint(getAllObj(grid, grid2, player, player2, shipSetting, shipSetting2, ship, shipCoordsSetting, shipSet, shipDelete, lastGuess, gameState, playerState)))
    pw.close()
  }

  def getAllObj(grid: InterfaceGrid, grid2: InterfaceGrid, player: InterfacePerson, player2x: InterfacePerson, shipSetting: Array[Int], shipSetting2: Array[Int], shipX: InterfaceShip, shipCoordsSettingX: Array[Int], shipSetX: Boolean, shipDeleteX: Boolean, lastGuessX: String, gameStateX: GameState, playerStateX: PlayerState): JsValue = {
    val array: Array[Array[Int]] = Array(shipSetting, shipSetting2, shipCoordsSettingX)
    val players: Array[InterfacePerson] = Array(player, player2x)
    val bools: Array[Boolean] = Array(shipSetX, shipDeleteX)
    Json.toJson(List(grid1ToJson(grid), grid2ToJson(grid2), playerToJson.writes(players),
      arrayToJson.writes(array), ship.writes(shipX), boolToJson.writes(bools),
      lastGuess.writes(lastGuessX), gameState.writes(gameStateX), playerState.writes(playerStateX)))
  }

  implicit val playerToJson = new Writes[Array[InterfacePerson]] {
    override def writes(player: Array[InterfacePerson]): JsValue = Json.obj(
      "players" -> Json.obj(
      "player1" -> Json.toJson(player(0).toString),
              "player2" -> Json.toJson(player(1).toString)
      )
    )
  }

  implicit val arrayToJson = new Writes[Array[Array[Int]]] {
    override def writes(array: Array[Array[Int]]): JsValue = Json.obj(
      "arraysInt" -> Json.obj(
      "shipSetting" -> Json.toJson(array(0)),
      "shipSetting2" -> Json.toJson(array(1)),
      "shipCoordsSet" -> Json.toJson(array(2))
      )
    )
  }

  implicit val ship = new Writes[InterfaceShip] {
    def writes(ship: InterfaceShip): JsValue = Json.obj(
      "ship" -> "" // @TODO needs Function
    )
  }

  implicit val boolToJson = new Writes[Array[Boolean]] {
    override def writes(bools: Array[Boolean]): JsValue = Json.obj(
      "booleans" -> Json.obj(
      "shipSet" -> Json.toJson(bools(0)),
             "shipDelete" -> Json.toJson(bools(1))
    )
    )
  }

  implicit val lastGuess = new Writes[String] {
    def writes(lastGuess: String): JsValue = Json.obj(
      "lastGuess" -> Json.toJson(lastGuess)
    )
  }

  implicit val gameState = new Writes[GameState] {
    def writes(gameState: GameState): JsValue = Json.obj(
      "gameState" -> gameState
    )
  }

  implicit val playerState = new Writes[PlayerState] {
    def writes(playerState: PlayerState): JsValue = Json.obj(
      "playerState" -> playerState
    )
  }

  implicit val grid2 = new Writes[InterfaceGrid] {
    def writes(grid2: InterfaceGrid): JsValue = grid2ToJson(grid2)
  }


  def grid1ToJson(grid: InterfaceGrid) = {
    val gridSize = grid.getSize
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
        "sizeX" -> JsNumber(gridSize),
        "cellsX" -> Json.toJson(
          for {
            row <- 0 until gridSize
            col <- 0 until gridSize
          } yield {
            Json.obj(
              "rowX" -> row,
              "colX" -> col,
              "valueX" -> grid.getValue(row, col)
            )
          }
        )
      )
    )
  }

}
