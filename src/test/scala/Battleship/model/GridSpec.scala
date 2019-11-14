package Battleship.model

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {

  "A Grid" when {
    "new" should {
      val grid = Grid(10)

      "getField" in {
        val tmp = grid.getField(0, 0)
        tmp should be(0)
      }


    }
  }

}
