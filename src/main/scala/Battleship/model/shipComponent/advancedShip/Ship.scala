package Battleship.model.shipComponent.advancedShip

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

import scala.collection.mutable

case class Ship(shipCoordinates: Array[Int], grid: Grid, strategyCollide: StrategyCollide) extends InterfaceShip {
  private val SCollides: StrategyCollide = strategyCollide

  private val size: Int = setSize
  private val coordinates = setCoordinates

  private def setCoordinates: Array[Array[Int]] = {
    val coordinate: Array[Array[Int]] = Array.ofDim[Int](size, 2)
    var idx = 0

    val x1 = shipCoordinates(0)
    val y1 = shipCoordinates(1)
    val x2 = shipCoordinates(2)
    val y2 = shipCoordinates(3)

    if (x1 == x2) {
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

  private def setSize(): Int = {
    val x1 = shipCoordinates(0)
    val y1 = shipCoordinates(1)
    val x2 = shipCoordinates(2)
    val y2 = shipCoordinates(3)

    if (x1 == x2) {
      y2 - y1 + 1
    } else {
      x2 - x1 + 1
    }
  }

  override def getSize: Int = size

  override def getCoordinates: Array[Array[Int]] = coordinates

  override def collide(ship: Ship, grid: Grid): Boolean = SCollides.collide(ship, grid)

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
