package Battleship.TUI

import Battleship.model.Creator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {
  "printWelcome" in {
  }
  "setPlayer" in {
  }
  "printGetPlayer" in {
  }
  "printSetField" in {
  }
  "askShips" in {
  }
  "printField" in {
  }
  "getRow" in {
    val testStrings: List[String] = List("A0","A1","A2","A3", "A4","A5","A6","A7","A8","A9")
    var idx = 0
    for (elem <- testStrings) {
      val testint = TUIMethods.getRow(elem)
      testint should be(idx)
      idx += 1
    }
  }
}
