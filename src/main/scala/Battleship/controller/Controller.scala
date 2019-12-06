package Battleship.controller

import Battleship.controller.GameState._
import Battleship.controller.PlayerState._
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import Battleship.model.{Creator, Player}
import Battleship.util.{Observable, UndoManager}

import scala.util.Try


class Controller(val grid_player_01: Grid, var grid_player_02: Grid) extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01: Player = Player("")
  var player_02: Player = Player("")
  val grid_player01: Grid = this.grid_player_01
  val grid_player02: Grid = this.grid_player_02
  var nr: Array[Int] = Array[Int](2, 0, 0, 0)
  var nr2: Array[Int] = Array[Int](1, 0, 0, 0)
  var ship: Ship = Ship(Array(0, 0, 0, 0), new StrategyCollideNormal)
  var shipCoordsSetting: Array[Int] = Array(0, 0, 0, 0)
  var shipSet: Boolean = false
  var shipDelete: Boolean = false

  var gameState: GameState = IDLE
  var playerState: PlayerState = PLAYER_ONE
  private val undoManager = new UndoManager

  def checkShipSetting(playerInput: String): Boolean = {

    Try {
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
          return false
        } else {
          print("Format Error\n")
        }
      }
    }.getOrElse {
      print("you have to input numbers\n")
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

  def checkGuess(playerInput: String, grid: Grid): PlayerState = {
    var hit = false

    Try {
      playerInput.split("\n").map { entry =>
        val token = entry.split(" ")
        if (token.length == 2) {
          val x = token(0).toInt
          val y = token(1).toInt

            grid.getValue(x, y) match {
              case 0 => grid.setField(x, y, 3)
              case 1 =>
                hit = true
                grid.setField(x, y, 2)
              case _ =>
            }
          } else {
            print("Format Error\n")
            hit = true
          }
        }
    }.getOrElse {
      print("you have to input numbers\n")
      hit = true
    }

    if (!hit) {
      playerState match {
        case PLAYER_ONE => PlayerState.PLAYER_TWO
        case PLAYER_TWO => PlayerState.PLAYER_ONE
      }
    } else {
      playerState
    }
  }

  def createShip(): Unit = {
    undoManager.createShip(new SetCommand(playerState, shipCoordsSetting, this))
    notifyObservers()
  }

  def setShips(): Unit = {
    undoManager.setShip(new SetCommand(playerState, shipCoordsSetting, this))
    notifyObservers()
  }

  def deleteShip(): Unit = {
    undoManager.undoCreate(new SetCommand(playerState, shipCoordsSetting, this))
    notifyObservers()
  }

  def shipToString(ship: Ship): String = {
    ship.toString
  }

  def gridToString(int: Int, boolean: Boolean): String = {
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

}
