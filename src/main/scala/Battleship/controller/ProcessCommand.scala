package Battleship.controller

import Battleship.controller.PlayerState.{PLAYER_ONE, PLAYER_TWO, PlayerState}
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.util.Command

import scala.util.Try

class ProcessCommand(playerInput: String, grid: Grid, playerState: PlayerState, controller: Controller) extends Command {
  override def setValue(): Unit = {
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
        case PLAYER_ONE => controller.playerState = PlayerState.PLAYER_TWO
        case PLAYER_TWO => controller.playerState = PlayerState.PLAYER_ONE
      }
    } else {
      controller.playerState = playerState
    }
  }

  override def undoStep(): Unit = {
    Try {
      playerInput.split("\n").map { entry =>
        val token = entry.split(" ")
        if (token.length == 2) {
          val x = token(0).toInt
          val y = token(1).toInt

          grid.getValue(x, y) match {
            case 2 =>
              grid.setField(x, y, 1)
            case 3 =>
              grid.setField(x, y, 0)
            case _ =>
          }
        }
      }
    }
  }
}
