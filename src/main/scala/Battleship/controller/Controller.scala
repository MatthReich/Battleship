package Battleship.controller

import Battleship.controller.GameStatus._
import Battleship.controller.PlayerStatus._
import Battleship.model.{Creator, Grid, Player}
import Battleship.util.{Observable, UndoManager}
import scala.util.Try

class Controller(val grid_player_01: Grid, var grid_player_02: Grid) extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01: Player = Player("")
  var player_02: Player = Player("")
  val grid_player01: Grid = this.grid_player_01
  val grid_player02: Grid = this.grid_player_02
  val nr: Array[Int] = Array[Int](1, 0, 0, 0)
  val nr2: Array[Int] = Array[Int](1, 0, 0, 0)

  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerStatus = PLAYER_ONE
  private val undoManager = new UndoManager

  def checkShipSetting(playerInput: String): Boolean = {
    val ship: Array[Int] = Array[Int](0, 0, 0, 0)

    Try {
      playerInput.split("\n").map { entry =>
        val input = entry.split(" ")
        if (input.length == 4) {

          ship(0) = input(0).toInt
          ship(1) = input(1).toInt
          ship(2) = input(2).toInt
          ship(3) = input(3).toInt

          return true
        } else {
          print("Format Error\n")
        }
      }
    }.getOrElse {
      print("you have to input numbers\n")
    }
    false
  }

  def addShips(int: Int, ship: Array[Int]): Unit = {
    undoManager.addShip(new SetCommand(int, ship, this))
    notifyObservers()
  }

  def checkGuess(playerInput: String, grid: Grid): PlayerStatus = {
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
      if (playerStatus == PLAYER_ONE) {
        PlayerStatus.PLAYER_TWO
      }
      else {
        PlayerStatus.PLAYER_ONE
      }
    } else {
      if (playerStatus == PLAYER_ONE) {
        PlayerStatus.PLAYER_ONE
      }
      else {
        PlayerStatus.PLAYER_TWO
      }
    }
  }

  def gridToString(int: Int, boolean: Boolean): String = {

    int match {
      case 0 =>
        if (boolean) {
          grid_player01.toString(player_01, boolean, playerStatus)
        } else {
          grid_player01.toString(player_01, boolean, playerStatus)
        }
      case 1 =>
        if (boolean) {
          grid_player02.toString(player_02, boolean, playerStatus)
        } else {
          grid_player02.toString(player_02, boolean, playerStatus)
        }
    }
  }

}
