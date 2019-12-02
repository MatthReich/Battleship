package Battleship.TUI


import Battleship.model.{Creator, Player}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {

  "printSetPlayer" in {
    var tmp = TUIMethods.printSetPlayer(1)
    tmp should include(Console.GREEN)
    tmp = TUIMethods.printSetPlayer(2)
    tmp should include(Console.CYAN)
  }

  "addShips" in {
    // can't be tested
  }

  "printWelcome" in {
    val tmp = TUIMethods.printWelcome(Creator("Marcel"), Creator("Matthias"))
    tmp should startWith("#")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should include("Battleship")
    tmp should endWith("\n")
  }

  "printGetPlayer" in {
    val tmp = TUIMethods.printGetPlayer(Player("Marcel"), Player("Matthias"))
    tmp should startWith("Actual")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should endWith(Console.RESET)
  }

}