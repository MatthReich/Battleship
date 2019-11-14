package Battleship.TUI

import Battleship.model.{Creator, Player}
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

  "printWelcome" in {
    val tmp = TUIMethods.printWelcome(new Creator("Marcel"), new Creator("Matthias"))
    tmp should startWith("#")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should include("Battleship")
    tmp should endWith("\n")
  }

  "printGetPlayer" in {
    val tmp = TUIMethods.printGetPlayer(new Player("Marcel"), new Player("Matthias"))
    tmp should startWith("Actual")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should endWith(Console.RESET)
  }
}
