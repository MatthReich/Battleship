package Battleship.model.shipComponent

import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

trait InterfaceShip extends StrategyCollide {

  def getSize: Int

  def getCoordinates: Array[Array[Int]]

  def collide(ship: Ship, grid: InterfaceGrid): Boolean

  def setToGrid(grid: InterfaceGrid): Boolean

  def deleteFromGrid(grid: InterfaceGrid): Unit

  def toString: String

  def setCoordinates(array: Array[Int]): Unit

}
