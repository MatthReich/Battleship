package Battleship.TUI

import Battleship.model.{Grid}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIProcessLineTest extends WordSpec with Matchers {

  "processLineIntern" in {
    val statement = TUIProcessLine.processLineIntern(true, "0 0", grid = Grid(10))
    statement should be (false)
  }
}