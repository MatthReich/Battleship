package Battleship.model.shipComponent.advancedShip

import Battleship.GameModule
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.strategyCollide.StrategyCollide
import com.google.inject.Guice

import scala.collection.mutable

case class Ship() extends InterfaceShip {
  val injector = Guice.createInjector(new GameModule)
  val strategyCollide: StrategyCollide = injector.getInstance(classOf[StrategyCollide])
  val SCollides: StrategyCollide = strategyCollide
  var shipCoordinates: Array[Int] = Array(0, 0, 0, 0)

  override def setToGrid(grid: InterfaceGrid): Boolean = {
    var shipSet: Boolean = false
    if (!collide(this, grid)) {
      if (shipCoordinates(0) == shipCoordinates(2)) {
        GridHelper(grid, 1)
      } else {
        GridHelper(grid, 1)
      }
      shipSet = true
    }
    shipSet
  }

  override def setCoordinates(array: Array[Int]): Unit = shipCoordinates = array

  override def collide(ship: Ship, grid: InterfaceGrid): Boolean = SCollides.collide(ship, grid)

  override def deleteFromGrid(grid: InterfaceGrid): Unit = {
    if (shipCoordinates(0) == shipCoordinates(2)) {
      GridHelper(grid, 0)
    } else {
      GridHelper(grid, 0)
    }
  }

  private def GridHelper(grid: InterfaceGrid, value: Int): Unit = {
    for (a <- shipCoordinates(0) to shipCoordinates(2)) {
      for (b <- shipCoordinates(1) to shipCoordinates(3)) {
        grid.setField(a, b, value)
      }
    }
  }

  override def toString: String = {
    val stringOfGrid = new mutable.StringBuilder("")
    val coordinates = getCoordinates
    for (x <- 0 until coordinates.length) {
      for (y <- 0 until 2) {
        stringOfGrid ++= coordinates(x)(y).toString
        stringOfGrid ++= " "
      }
      stringOfGrid ++= "\n"
    }
    stringOfGrid.toString()
  }

  override def getCoordinates: Array[Array[Int]] = {
    val coordinate: Array[Array[Int]] = Array.ofDim[Int](getSize, 2)
    var idx = 0
    for (x <- shipCoordinates(0) to shipCoordinates(2)) {
      for (y <- shipCoordinates(1) to shipCoordinates(3)) {
        coordinate(idx)(0) = x
        coordinate(idx)(1) = y
        idx += 1
      }
    }
    coordinate
  }

  override def getSize: Int = {
    if (shipCoordinates(0) == shipCoordinates(2)) {
      getSizeHelper(3, 1)
    } else {
      getSizeHelper(2, 0)
    }
  }

  private def changeParam: Unit = {
    var tmp = shipCoordinates(0)
    shipCoordinates(0) = shipCoordinates(2)
    shipCoordinates(2) = tmp
    tmp = shipCoordinates(1)
    shipCoordinates(1) = shipCoordinates(3)
    shipCoordinates(3) = tmp
  }

  private def getSizeHelper(x: Int, y: Int): Int = {
    var size = shipCoordinates(x) - shipCoordinates(y) + 1
    if (size > 0) {
      size
    } else {
      size = shipCoordinates(y) - shipCoordinates(x) + 1
      changeParam
      size
    }
  }

}
