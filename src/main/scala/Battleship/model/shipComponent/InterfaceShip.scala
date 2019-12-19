package Battleship.model.shipComponent

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

trait InterfaceShip extends StrategyCollide {

  def getSize: Int

  def getCoordinates: Array[Array[Int]]

  def collide(ship: Ship, grid: Grid): Boolean

  def setToGrid(grid: Grid): Boolean

  def deleteFromGrid(grid: Grid): Unit

  def toString: String

}
