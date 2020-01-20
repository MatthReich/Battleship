package Battleship.model

import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import org.scalatest.{Matchers, WordSpec}

class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val grid: InterfaceGrid = new Grid
      val ship: Ship = new Ship

      "getSize" in {
        ship.getSize should be(1)
        ship.setCoordinates(Array(1, 1, 5, 1))
        ship.getSize should be(5)
        ship.setCoordinates(Array(2, 2, 2, 0))
        ship.getSize should be(3)
      }

      "coordinates" in {
        ship.setCoordinates(Array(4, 4, 4, 0))
        ship.getCoordinates should be(Array(Array(4, 0), Array(4, 1), Array(4, 2), Array(4, 3), Array(4, 4)))
      }

      "collides + setToGrid" in {
        val testShipCollide: Ship = new Ship
        ship.setToGrid(grid) should be(true)
        testShipCollide.setCoordinates(Array(4, 0, 4, 4))
        ship.collide(testShipCollide, grid) should be(true)
        testShipCollide.setToGrid(grid) should be(false)
        testShipCollide.setCoordinates(Array(5, 5, 5, 7))
        ship.collide(testShipCollide, grid) should be(false)
        testShipCollide.setToGrid(grid) should be(true)
      }

      "deleteFromGrid" in {
        val testShip = new Ship
        testShip.setCoordinates(Array(0, 0, 0, 1))
        val testGrid: InterfaceGrid = new Grid
        testShip.setToGrid(testGrid) should be(true)
        testGrid.getValue(0, 0) should be(1)
        testShip.deleteFromGrid(testGrid)
        testGrid.getValue(0, 0) should be(0)
        testShip.setCoordinates(Array(0, 0, 1, 0))
        testShip.setToGrid(testGrid) should be(true)
        testGrid.getValue(0, 0) should be(1)
        testShip.deleteFromGrid(testGrid)
        testGrid.getValue(0, 0) should be(0)
      }

      "toString" in {
        ship.toString should be("4 0 \n4 1 \n4 2 \n4 3 \n4 4 \n")
      }

    }
  }
}
