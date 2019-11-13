package Battleship.TUI

import Battleship.model.Creator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {
  "setPlayer" in {
    // can't be tested
  }


  "printSetField" in {
    // can't be tested
  }

  "askShips" in {
    // can't be tested
  }

  "getRow" in {
    val testStrings: List[String] = List("A0","A1","A2","A3", "A4","A5","A6","A7","A8","A9")
    var idx = 0
    for (elem <- testStrings) {
      val testInt = TUIMethods.getRow(elem)
      testInt should be(idx)
      idx += 1
    }
  }
}
