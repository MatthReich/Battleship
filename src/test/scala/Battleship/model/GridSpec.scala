package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GridSpec extends WordSpec with Matchers {

  "A Grid" when {
    "new" should {
      val grid = Grid(10)
      val gridShort = Grid(1)

      "getField" in {
        val tmp = grid.getField(0, 0)
        tmp should be(0)
      }

      "setField" in {
        grid.setField(0, 0, 1)
        val tmp = grid.getField(0, 0)
        tmp should be(1)
      }

      "setShip" in {
        grid.setShip(Array[Int](0, 0, 2, 0), Array[Int](1, 1, 1, 1))
        var tmp = grid.getField(2, 0)
        tmp should be(1)
        grid.setShip(Array[Int](1, 1, 1, 2), Array[Int](1, 1, 1, 1))
        tmp = grid.getField(1, 2)
        tmp should be(1)
        grid.setShip(Array[Int](2, 2, 2, 3), Array[Int](0, 1, 1, 1))
        tmp = grid.getField(2, 3)
        tmp should be(0)
        grid.setShip(Array[Int](0, 0, 3, 0), Array[Int](1, 1, 1, 1))
        tmp = grid.getField(3, 0)
        tmp should be(0)
      }

      "getValue" in {
        gridShort.setField(0, 0, 1)
        gridShort.getValue(0, 0) should be (1)
        gridShort.setField(0, 0, 0)
        gridShort.getValue(0, 0) should be (0)
      }

      "winStatement" in {
        var check = gridShort.winStatement()
        check should be (true)
        gridShort.setField(0, 0, 1)
        check = gridShort.winStatement()
        check should be (false)
      }

    }
  }

}
