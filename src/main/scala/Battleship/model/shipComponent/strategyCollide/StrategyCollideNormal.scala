package Battleship.model.shipComponent.strategyCollide

import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.advancedShip.Ship

class StrategyCollideNormal extends StrategyCollide {

  override def collide(newShip: Ship, grid: InterfaceGrid): Boolean = {
    val shipCoordinates = newShip.getCoordinates
    for (x <- 0 until grid.getSize) {
      for (y <- 0 until grid.getSize) {
        if (grid.getValue(x, y) == 1)
          if (equalsShip(x, y, shipCoordinates)) {
            return true
          }
      }
    }
    false
  }

  private def equalsShip(getX: Int, getY: Int, coordinates: Array[Array[Int]]): Boolean = {
    for (x <- 0 until coordinates.length) {
      if (getX == coordinates(x)(0) && getY == coordinates(x)(1))
        return true
    }
    false
  }

}
