package Battleship.model.shipComponent.strategyCollide

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship

class StrategyCollideNormal extends StrategyCollide {
  override def collide(newShip: Ship, grid: Grid): Boolean = {
    true
  }
}
