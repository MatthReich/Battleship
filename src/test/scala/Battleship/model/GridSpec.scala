package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GridSpec extends WordSpec with Matchers {

  "A Grid" when {
    "new" should {
      val grid = Grid(10)

      "getField" in {
        val tmp = grid.getField(0, 0)
        tmp should be(0)
      }

      "setField" in {
        grid.setField(0, 0, 1)
        val tmp = grid.getField(0, 0)
        tmp should be(1)
      }
    }
  }

}
