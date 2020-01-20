package Battleship.controller

import Battleship.controller.PlayerState.PlayerState
import Battleship.util.Command

class SetCommand(player: PlayerState, ship: Array[Int], controller: InterfaceController) extends Command {

  override def setValue(): Unit = {
    controller.setShipDelete(false)
    if (player == PlayerState.PLAYER_ONE) {
      controller.setShipSet(controller.getShip.setToGrid(controller.getGridPlayer1))
    }
    else {
      controller.setShipSet(controller.getShip.setToGrid(controller.getGridPlayer2))
    }
  }

  override def undoStep(): Unit = {
    if (player == PlayerState.PLAYER_ONE) {
      controller.getShip.deleteFromGrid(controller.getGridPlayer1)
    }
    else {
      controller.getShip.deleteFromGrid(controller.getGridPlayer2)
    }
    controller.setShipSet(false)
    controller.setShipDelete(true)
  }
}
