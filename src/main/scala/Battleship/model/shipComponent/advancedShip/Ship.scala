package Battleship.model.shipComponent.advancedShip

import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

case class Ship(shipCoordinates: Array[Int], strategyCollide: StrategyCollide) extends InterfaceShip {
  val collides: StrategyCollide = strategyCollide



  val size = shipCoordinates.length

  override def getSize: Int = size

  override def collide(ship: Ship): Boolean = collides.collide(ship)

}
