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

      "getSize" in {
        val tmp = grid.getSize()
        tmp should be(10)
      }

      "setField" in {
        grid.setField(0, 0, 1)
        val tmp = grid.getField(0, 0)
        tmp should be(1)
      }



    }
  }

}
