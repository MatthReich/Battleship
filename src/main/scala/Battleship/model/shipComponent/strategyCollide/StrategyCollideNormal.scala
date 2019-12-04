package Battleship.model.shipComponent.strategyCollide

import Battleship.model.shipComponent.advancedShip.{Ship}

class StrategyCollideNormal extends StrategyCollide {
  override def collide(ship: Ship): Boolean = {
    true
  }
}
