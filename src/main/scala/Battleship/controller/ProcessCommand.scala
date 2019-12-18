package Battleship.controller

import Battleship.controller.PlayerState.{PLAYER_ONE, PLAYER_TWO, PlayerState}
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.util.Command

import scala.util.{Failure, Success, Try}

class ProcessCommand(playerInput: String, grid: InterfaceGrid, playerState: PlayerState, controller: Controller) extends Command {

  override def setValue(): Unit = {
    var hit = false
    var functionable: Boolean = true

    val myString = playerInput.split(" ")

    val convertDoubles = myString.map { x =>
      Try(x.toInt)
    }

    val convertedArray = convertDoubles.map {
      case Success(res) => res
      case Failure(f) => None
    }

    for (x <- convertedArray) {
      if (x == None) {
        functionable = false
        hit = true
        print("wrong input")
      }
    }

    if (functionable) {
      if (true) {

        val x: Int = convertedArray(0).toString.toInt
        val y: Int = convertedArray(1).toString.toInt

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

    if (convertedArray.length != 2) {
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
