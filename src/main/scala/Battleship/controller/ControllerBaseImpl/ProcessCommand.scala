package Battleship.controller.ControllerBaseImpl

import Battleship.controller.ControllerBaseImpl.PlayerState.{PLAYER_ONE, PLAYER_TWO, PlayerState}
import Battleship.controller.InterfaceController
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.util.Command

import scala.util.{Failure, Success, Try}

class ProcessCommand(playerInput: String, grid: InterfaceGrid, playerState: PlayerState, controller: InterfaceController) extends Command {

  override def setValue(): Unit = {
    var hit = false
    var functionable: Boolean = true
    var convertedArray: Array[Any] = new Array[Any](0)

    val myString = playerInput.split(" ")

    if (myString.length != 2) {
      functionable = false
      hit = true
      print("wrong output")
    } else {
      val convertDoubles = myString.map { x =>
        Try(x.toInt)
      }

      convertedArray = convertDoubles.map {
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
    }

    if (functionable) {
      val x: Int = convertedArray(0).toString.toInt
      val y: Int = convertedArray(1).toString.toInt

      grid.getValue(x, y) match {
        case 0 => grid.setField(x, y, 3)
        case 1 =>
          hit = true
          grid.setField(x, y, 2)
        case _ =>
      }
    }

    if (!hit) {
      playerState match {
        case PLAYER_ONE => controller.setPlayerState(PlayerState.PLAYER_TWO)
        case PLAYER_TWO => controller.setPlayerState(PlayerState.PLAYER_ONE)
      }
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
