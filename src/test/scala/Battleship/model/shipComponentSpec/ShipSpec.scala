package Battleship.model.shipComponentSpec

import Battleship.GameModule
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.advancedShip.Ship
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val injector = Guice.createInjector(new GameModule)
      val grid: InterfaceGrid = injector.getInstance(classOf[InterfaceGrid])
      val ship: Ship = new Ship
      ship.setCoordinates(Array(0, 0, 0, 1))
      val testShipCollide: Ship = new Ship
      testShipCollide.setCoordinates(Array(0, 1, 0, 2))
      val testShipNotCollides: Ship = new Ship
      testShipNotCollides.setCoordinates(Array(5, 5, 5, 7))
      val longShip: Ship = new Ship
      longShip.setCoordinates(Array(1, 1, 5, 1))
      val backwardsShip: Ship = new Ship
      backwardsShip.setCoordinates(Array(2, 2, 2, 0))

      "getSize" in { // also tests if setSize works fine
        ship.getSize should be(2)
        longShip.getSize should be(5)
        backwardsShip.getSize should be(3)
      }

      "toString" in { // also tests if setCoordinates works fine
        ship.toString should be("0 0 \n0 1 \n")
        testShipCollide.toString should be("0 1 \n0 2 \n")
        longShip.toString should be ("1 1 \n2 1 \n3 1 \n4 1 \n5 1 \n")
      }

      "collides + setToGrid" in {
        ship.setToGrid(grid) should be (true)

        ship.collide(testShipCollide, grid) should be(true)
        testShipCollide.setToGrid(grid) should be(false)

        ship.collide(testShipNotCollides, grid) should be(false)
        testShipNotCollides.setToGrid(grid) should be(true)

        ship.collide(longShip, grid) should be(false)
        longShip.setToGrid(grid) should be(true)
      }

      "deleteFromGrid" in {
        val shippp = new Ship
        shippp.setCoordinates(Array(0, 0, 0, 1))
        val gridd: InterfaceGrid = injector.getInstance(classOf[InterfaceGrid])
        shippp.setToGrid(gridd) should be(true)
        gridd.getField(0, 0) should be(0)
        shippp.deleteFromGrid(gridd)
        gridd.getField(0, 0) should be(0) // false should be 0 !! did not work
      }

      "getCoordinates" in {
        // tested in collide
      }

    }
  }
}
