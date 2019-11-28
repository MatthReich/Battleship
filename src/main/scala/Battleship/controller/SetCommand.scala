package Battleship.controller

import Battleship.util.Command

class SetCommand(player: Int, ship: Array[Int], controller: Controller) extends Command {

  override def addShip: Unit = {
    if (player == 0) controller.grid_player01.setShip(ship, controller.nr) // player: Int -> 0 = player1, 1 = player2
    else controller.grid_player02.setShip(ship, controller.nr2)
  }

}
