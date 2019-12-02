package Battleship.controller

import Battleship.controller.GameStatus._
import Battleship.controller.PlayerStatus._
import Battleship.model.{Creator, Grid, Player}
import Battleship.util.{Observable, UndoManager}

//noinspection ScalaStyle
class Controller(val grid_player_01: Grid, var grid_player_02: Grid) extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01: Player = Player("")
  var player_02: Player = Player("")
  val grid_player01: Grid = this.grid_player_01
  val grid_player02: Grid = this.grid_player_02
  val nr: Array[Int] = Array[Int](1, 0, 0, 0)
  val nr2: Array[Int] = Array[Int](1, 0, 0, 0)

  var hit = false

  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerStatus = PLAYER_ONE
  private val undoManager = new UndoManager

  def checkShipSetting(playerInput: String): Array[Int] = { //??wenn input (1,1,1,a) => output (1,1,1,0)
    val input = playerInput.split(" ")
    val ship: Array[Int] = new Array[Int](4)
    try {
      if (input.length == 4) {
        ship(0) = input(0).toInt
        ship(1) = input(1).toInt
        ship(2) = input(2).toInt
        ship(3) = input(3).toInt
      }
    } catch {
      case _: NumberFormatException => print("you have to input numbers\n")
    }
    ship
  }

  def addShips(int: Int, ship: Array[Int]): Unit = {
    undoManager.addShip(new SetCommand(int, ship, this))
    notifyObservers()
  }

  def checkGuess(playerInput: String, grid: Grid): PlayerStatus = {
    hit = false
    val input = playerInput.split(" ")

    try {
      if (input.length == 2) { // schöner mit for <- schleife lösen
        val x = input(0).toInt
        val y = input(1).toInt

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
    } catch {
      case _: NumberFormatException => println("you have to input numbers\n")
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
