package Battleship.model

import Battleship.model.gridComponent.advancedGrid.Grid
import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {

  "A Grid" when {
    "new" should {
      val grid = new Grid

      "setField" in {
        grid.setField(0, 0, 1)
        val tmp = grid.getValue(0, 0)
        tmp should be(1)
      }

      "getField" in {
        var tmp = grid.getField(0, 0)
        tmp should be(0)
        tmp = grid.getField(1, 0)
        tmp should be(0)
      }

      "getValue" in {
        grid.getValue(0, 0) should be(1)
        grid.getValue(1, 0) should be(0)
      }

      "size" in {
        grid.setSize(1)
        grid.getSize should be(1)
      }

      "winStatement" in {
        grid.setField(0, 0, 0)
        grid.winStatement() should be(true)
        grid.setField(0, 0, 1)
        grid.winStatement() should be(false)
      }
    }
  }

}
