package Battleship.model.shipComponentSpec

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val grid: Grid = Grid(10)
      val ship: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
      val testShipCollide: Ship = Ship(Array(0, 1, 0, 2), new StrategyCollideNormal)
      val testShipNotCollides: Ship = Ship(Array(5, 5, 5, 7), new StrategyCollideNormal)
      val longShip: Ship = Ship(Array(1, 1, 5, 1), new StrategyCollideNormal)
      val backwardsShip: Ship = Ship(Array(2, 2, 2, 0), new StrategyCollideNormal)

      "getSize" in { // also tests if setSize works fine
        ship.getSize should be (2)
        longShip.getSize should be(5)
        backwardsShip.getSize should be(3)
      }

      "toString" in { // also tests if setCoordinates works fine
        ship.toString should be ("0 0 \n0 1 \n")
        testShipCollide.toString should be ("0 1 \n0 2 \n")
        longShip.toString should be ("1 1 \n2 1 \n3 1 \n4 1 \n5 1 \n")
      }

      "collides + setToGrid" in {
        ship.setToGrid(grid) should be (true)

        ship.collide(testShipCollide, grid) should be (true)
        testShipCollide.setToGrid(grid) should be (false)

        ship.collide(testShipNotCollides, grid) should be (false)
        testShipNotCollides.setToGrid(grid) should be (true)

        ship.collide(longShip, grid) should be (false)
        longShip.setToGrid(grid) should be (true)
      }

      "getCoordinates" in {
        // tested in collide
      }

    }
  }
}
