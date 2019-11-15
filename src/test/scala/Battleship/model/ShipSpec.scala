package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ShipSpec extends WordSpec with Matchers {

  "A Ship" when {
    "new" should {
      val coordinatsQuer = Array(1, 2, 1, 3)
      val coordinatsSenk = Array(1, 2, 3, 2)
      val coordinatsColidate = Array(4, 4, 4, 5)
      val coords = Array(1, 2, 3, 4)
      val coord2 = Array(1, 1, 1, 2)

      val shipQuer = Ship(coordinatsQuer)
      val shipSenk = Ship(coordinatsSenk)


      "setCoordinates" in {
        shipQuer.setCoordinates()
        shipQuer.coordinats(0)(0) should be (1)
        shipQuer.coordinats(0)(1) should be (2)
        shipQuer.coordinats(1)(0) should be (1)
        shipQuer.coordinats(1)(1) should be (3)

        shipSenk.setCoordinates()
        shipSenk.coordinats(0)(0) should be (1)
        shipSenk.coordinats(0)(1) should be (2)
        shipSenk.coordinats(2)(0) should be (3)
        shipSenk.coordinats(2)(1) should be (2)
      }

      "colidate" in {
        shipQuer.colidate(coordinatsSenk) should be (true)
        shipQuer.colidate(coordinatsColidate) should be (false)
        shipQuer.colidate(coords) should be (true)
        shipQuer.colidate(coord2) should be (true)



      }
    }
  }

}
