package Battleship.controller

import Battleship.controller.PlayerStatus.PlayerStatus
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import Battleship.util.Command

class SetCommand(player: PlayerStatus, ship: Array[Int], controller: Controller) extends Command {

  override def createShip(): Unit = {
    // controller.ship = Ship(controller.shipCoordsSetting, new StrategyCollideNormal)
  }

  override def setShip(): Unit = { // player: Int -> 0 = player1, 1 = player2
    if (player == PlayerStatus.PLAYER_ONE) {
      controller.shipSet = controller.ship.setToGrid(controller.grid_player01)
    }
    else {
      controller.shipSet = controller.ship.setToGrid(controller.grid_player02)
    }
  }

}
