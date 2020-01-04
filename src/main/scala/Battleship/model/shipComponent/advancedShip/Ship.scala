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
  private val SCollides: StrategyCollide = strategyCollide
  var shipCoordinates: Array[Int] = Array(0, 0, 0, 0)
  private val coordinates = setCoordinates()

  override def deleteFromGrid(grid: InterfaceGrid): Unit = {

    if (shipCoordinates(0) == shipCoordinates(2)) {
      for (x <- shipCoordinates(1) to shipCoordinates(3)) {
        for (y <- shipCoordinates(0) to shipCoordinates(2)) {
          grid.setField(y, x, 0)
        }
      }
    } else {
      for (x <- shipCoordinates(0) to shipCoordinates(2)) {
        for (y <- shipCoordinates(1) to shipCoordinates(3)) {
          grid.setField(x, y, 0)
        }
      }
    }
  }

  override def setToGrid(grid: InterfaceGrid): Boolean = {
    var shipSet: Boolean = false
    if (!collide(this, grid)) {
      if (shipCoordinates(0) == shipCoordinates(2)) {
        for (x <- shipCoordinates(1) to shipCoordinates(3)) {
          for (y <- shipCoordinates(0) to shipCoordinates(2)) {
            grid.setField(y, x, 1)
          }
        }
      } else {
        for (x <- shipCoordinates(0) to shipCoordinates(2)) {
          for (y <- shipCoordinates(1) to shipCoordinates(3)) {
            grid.setField(x, y, 1)
          }
        }
      }
      shipSet = true
    }
    shipSet
  }

  override def setCoordinates(array: Array[Int]): Unit = shipCoordinates = array

  private def setCoordinates(): Array[Array[Int]] = {
    val coordinate: Array[Array[Int]] = Array.ofDim[Int](getSize, 2)
    var idx = 0

    if (shipCoordinates(0) == shipCoordinates(2)) {
      for (x <- shipCoordinates(1) to shipCoordinates(3)) {
        for (y <- shipCoordinates(0) to shipCoordinates(2)) {
          coordinate(idx)(0) = y
          coordinate(idx)(1) = x
          idx += 1
        }
      }
      coordinate
    } else {
      for (x <- shipCoordinates(0) to shipCoordinates(2)) {
        for (y <- shipCoordinates(1) to shipCoordinates(3)) {
          coordinate(idx)(0) = x
          coordinate(idx)(1) = y
          idx += 1
        }
      }
      coordinate
    }
  }

  override def getSize: Int = setSize()

  private def setSize(): Int = {
    if (shipCoordinates(0) == shipCoordinates(2)) {
      val s = shipCoordinates(3) - shipCoordinates(1) + 1
      if (s > 0) {
        s
      }
      else {
        val size = shipCoordinates(1) - shipCoordinates(3) + 1
        changeParam
        size
      }
    } else {
      val s = shipCoordinates(2) - shipCoordinates(0) + 1
      if (s > 0) {
        s
      }
      else {
        val size = shipCoordinates(0) - shipCoordinates(2) + 1
        changeParam
        size
      }
    }
  }

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

  private def changeParam: Unit = {
    var tmp = shipCoordinates(0)
    shipCoordinates(0) = shipCoordinates(2)
    shipCoordinates(2) = tmp
    tmp = shipCoordinates(1)
    shipCoordinates(1) = shipCoordinates(3)
    shipCoordinates(3) = tmp
  }
}
