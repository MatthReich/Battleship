package Battleship.controller

import Battleship.controller.PlayerState.PlayerState
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import Battleship.util.Command

class SetCommand(player: PlayerState, ship: Array[Int], controller: Controller) extends Command {

  override def createShip(): Unit = {
    controller.shipDelete = false
    controller.ship = Ship(controller.shipCoordsSetting, new StrategyCollideNormal)
  }

  override def setShip(): Unit = { // player: Int -> 0 = player1, 1 = player2
    controller.shipDelete = false
    if (player == PlayerState.PLAYER_ONE) {
      controller.shipSet = controller.ship.setToGrid(controller.grid_player01)
    }
    else {
      controller.shipSet = controller.ship.setToGrid(controller.grid_player02)
    }
  }

  override def undoCreate(): Unit = {
    if (player == PlayerState.PLAYER_ONE) {
      controller.ship.deleteFromGrid(controller.grid_player01)
    }
    else {
      controller.ship.deleteFromGrid(controller.grid_player02)
    }
    controller.shipSet = false
    controller.shipDelete = true
  }
}
