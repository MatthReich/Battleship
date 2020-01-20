package Battleship.model.shipComponent.strategyCollide

import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.advancedShip.Ship

trait StrategyCollide {

  def collide(ship: Ship, grid: InterfaceGrid): Boolean

}
