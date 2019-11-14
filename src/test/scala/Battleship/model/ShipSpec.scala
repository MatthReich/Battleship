package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val coordinats = Array(1, 2, 1, 3)
      val ship = Ship(coordinats)

      "getCoordinates" in {
        val shipOutput = ship.getCoordinates()
      }

      "getSize" in {
        ship.getSize() should be (2)
      }

      "insertCoordinates" in {
        // tested through getCoordinates ??
      }
    }
  }

}
