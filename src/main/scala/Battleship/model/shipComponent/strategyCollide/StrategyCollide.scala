package Battleship.model.shipComponent.strategyCollide

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship

trait StrategyCollide {

  def collide(ship: Ship, grid: Grid): Boolean

}
