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
      val ship: Ship = Ship(Array(0, 0, 0, 1), grid, new StrategyCollideNormal)
      val testShipCollide: Ship = Ship(Array(0, 1, 0, 2), grid, new StrategyCollideNormal)
      val testShipNotCollides: Ship = Ship(Array(5, 5, 5, 7), grid, new StrategyCollideNormal)
      val longShip: Ship = Ship(Array(1, 1, 5, 1), grid, new StrategyCollideNormal)

      "getSize" in {
        // until method is created: then one of [2,3,4,5]
        ship.getSize should be (2)
      }

      "collides" in {
        // until method is created: then true
        ship.collide(testShipCollide, grid) should be (false)
        ship.collide(testShipNotCollides, grid) should be (false)
      }

      "getCoordinates" in {
        ship.toString should be ("0 0 \n0 1 \n")
        testShipCollide.toString should be ("0 1 \n0 2 \n")
        longShip.toString should be ("1 1 \n2 1 \n3 1 \n4 1 \n5 1 \n")
      }
    }
  }
}
