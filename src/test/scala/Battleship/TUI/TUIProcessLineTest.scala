package Battleship.TUI

import Battleship.model.{Grid}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIProcessLineTest extends WordSpec with Matchers {

  val grid = Grid(1)

  "processLineIntern" in {
    var statement = TUIProcessLine.processLineIntern(true, "0 0", grid)
    statement should be (false)

    grid.setField(0, 0, 1)
    statement = TUIProcessLine.processLineIntern(false, "0 0", grid)
    grid.getValue(0, 0) should be (2)
    statement should be (false)

    statement = TUIProcessLine.processLineIntern(true, "a 0", grid)
    statement should be (true)

    grid.setField(0, 0, 3)
    statement = TUIProcessLine.processLineIntern(true, "0 0", grid)
    statement should be (false)

    statement = TUIProcessLine.processLineIntern(true, "00", grid)
    statement should be (true)
  }
}