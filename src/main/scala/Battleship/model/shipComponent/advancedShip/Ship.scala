package Battleship.model.shipComponent.advancedShip

import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.strategyCollide.StrategyCollide

import scala.collection.mutable

case class Ship(shipCoordinates: Array[Int], strategyCollide: StrategyCollide) extends InterfaceShip {
  private val SCollides: StrategyCollide = strategyCollide

  private var x1 = shipCoordinates(0)
  private var y1 = shipCoordinates(1)
  private var x2 = shipCoordinates(2)
  private var y2 = shipCoordinates(3)

  private val size: Int = setSize()
  private val coordinates = setCoordinates()

  private def setCoordinates(): Array[Array[Int]] = {
    val coordinate: Array[Array[Int]] = Array.ofDim[Int](size, 2)
    var idx = 0

    if (x1 == x2) {
      for (x <- y1 to y2) {
        for (y <- x1 to x2) {
          coordinate(idx)(0) = y
          coordinate(idx)(1) = x
          idx += 1
        }
      }
      coordinate
    } else {
      for (x <- x1 to x2) {
        for (y <- y1 to y2) {
          coordinate(idx)(0) = x
          coordinate(idx)(1) = y
          idx += 1
        }
      }
      coordinate
    }
  }

  override def deleteFromGrid(grid: InterfaceGrid): Unit = {

    if (x1 == x2) {
      for (x <- y1 to y2) {
        for (y <- x1 to x2) {
          grid.setField(y, x, 0)
        }
      }
    } else {
      for (x <- x1 to x2) {
        for (y <- y1 to y2) {
          grid.setField(x, y, 0)
        }
      }
    }
  }

  private def setSize(): Int = {
    if (x1 == x2) {
      val s = y2 - y1 + 1
      if (s > 0) {
        s
      }
      else {
        val size = y1 - y2 + 1
        changeParam
        size
      }
    } else {
      val s = x2 - x1 + 1
      if (s > 0) {
        s
      }
      else {
        val size = x1 - x2 + 1
        changeParam
        size
      }
    }
  }

  override def setToGrid(grid: InterfaceGrid): Boolean = {
    var shipSet: Boolean = false
    if (!collide(this, grid)) {
      if (x1 == x2) {
        for (x <- y1 to y2) {
          for (y <- x1 to x2) {
            grid.setField(y, x, 1)
          }
        }
      } else {
        for (x <- x1 to x2) {
          for (y <- y1 to y2) {
            grid.setField(x, y, 1)
          }
        }
      }
      shipSet = true
    }
    shipSet
  }

  private def changeParam: Unit = {
    var tmp = x1
    x1 = x2
    x2 = tmp
    tmp = y1
    y1 = y2
    y2 = tmp
  }

  override def getSize: Int = size

  override def getCoordinates: Array[Array[Int]] = coordinates

  override def collide(ship: Ship, grid: InterfaceGrid): Boolean = SCollides.collide(ship, grid)

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
