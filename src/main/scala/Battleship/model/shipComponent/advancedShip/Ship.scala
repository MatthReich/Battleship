package Battleship.model.shipComponent.advancedShip

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

import scala.collection.mutable

case class Ship(shipCoordinates: Array[Int], grid: Grid, strategyCollide: StrategyCollide) extends InterfaceShip {
  private val collides: StrategyCollide = strategyCollide

  private val coordinates = setCoordinates
  private var size = 2

  private def setCoordinates: Array[Array[Int]] = {
    var coordinate: Array[Array[Int]] = Array.ofDim[Int](5, 2)
    var idx = 0

    val x1 = shipCoordinates(0)
    val y1 = shipCoordinates(1)
    val x2 = shipCoordinates(2)
    val y2 = shipCoordinates(3)

    if (x1 == x2) {
      size = y2 - y1 + 1
      coordinate = Array.ofDim[Int](size, 2)
      for (x <- y1 to y2) {
        for (y <- x1 to x2) {
          coordinate(idx)(0) = y
          coordinate(idx)(1) = x
          grid.setField(y, x, 1)
          idx += 1
        }
      }
      coordinate
    } else {
      size = x2 - x1 + 1
      coordinate = Array.ofDim[Int](size, 2)
      for (x <- x1 to x2) {
        for (y <- y1 to y2) {
          coordinate(idx)(0) = x
          coordinate(idx)(1) = y
          grid.setField(x, y, 1)
          idx += 1
        }
      }
      coordinate
    }
  }

  override def getSize: Int = size

  override def getCoordinates: Array[Array[Int]] = coordinates

  override def collide(ship: Ship, grid: Grid): Boolean = collides.collide(ship, grid)

  override def toString: String = {
    val stringOfGrid = new mutable.StringBuilder("")
    for (x <- 0 until coordinates.length) {
      for (y <- 0 until 2) {
        stringOfGrid ++= coordinates(x)(y).toString
        stringOfGrid ++= " "
      }
      stringOfGrid ++= "\n"
    }
    stringOfGrid.toString()
  }

}
