package Battleship.model.shipComponent.strategyCollide

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship

class StrategyCollideNormal extends StrategyCollide {


  override def collide(newShip: Ship, grid: Grid): Boolean = {
    val shipCoordinates = newShip.getCoordinates
    var collide = false
    for (x <- 0 until shipCoordinates.length) {
      for (y <- 0 until 2) {
        if (grid.getValue(x, y) == 1)
          if (equalsShip(x, y, shipCoordinates)) {
            collide = true
          }
      }
    }
    collide
  }

  private def equalsShip(getX: Int, getY: Int, coordinates: Array[Array[Int]]): Boolean = {
    var equal = false
    for (x <- 0 until coordinates.length) {
      for (y <- 0 until 2) {
        if (coordinates(x)(y) == getX && coordinates(x)(y) == getY) equal = true
      }
    }
    equal
  }

}
