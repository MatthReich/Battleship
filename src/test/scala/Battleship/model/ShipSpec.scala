package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val coordinats = Array(1, 2, 1, 3)
      val ship = Ship(coordinats)


      "insertCoordinates" in {
        // tested through getCoordinates ??
      }
    }
  }

}
