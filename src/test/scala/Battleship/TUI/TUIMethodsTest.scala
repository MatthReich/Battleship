package Battleship.TUI

import Battleship.model.Creator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {
  "printWelcome" in {
  val string = TUIMethods.printWelcome()
    string should startWith("#")
    string should endWith("\n")
    string should include(TUIInterface.creator_01.toString)
    string should include(TUIInterface.creator_02.toString)
    string should include("Battleship")
  }
  "setPlayer" in {
  }
  "printGetPlayer" in {
    val string = TUIMethods.printGetPlayer()
    string should startWith("Actual")
    string should endWith("\n" + Console.RESET)
    string should include(TUIInterface.player_01.toString)
    string should include(TUIInterface.player_02.toString)
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
