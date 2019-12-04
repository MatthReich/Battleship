package Battleship.model.shipComponentSpec

import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val ship: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
      val testShipCollide: Ship = Ship(Array(0, 1, 0, 2), new StrategyCollideNormal)
      val testShipNotCollides: Ship = Ship(Array(5, 5, 5, 6), new StrategyCollideNormal)

      "getSize" in {
        // until method is created: then one of [2,3,4,5]
        ship.getSize should be (4)
      }

      "collides" in {
        ship.collide(testShipCollide) should be (true)
        // until method is created: then false
        ship.collide(testShipNotCollides) should be (true)
      }
    }
  }
}
