package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.{Creator, Grid, Player}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIInterfaceTest extends WordSpec with Matchers {

  val interface = new TUIInterface(new Controller(Grid(10), Grid(10)))

  "A TUIInterface" when {
    "new" should {

      "update" in {
        val tmp = interface.update
        tmp should be(true)
      }

      "playerConfiguration" in {
        interface.playerConfiguration()
      }

      "setPlayers" in {
        // can't be tested
      }

      "printWelcomeX" in {
        interface.printWelcomeX()
      }

      "printSetPlayer" in {
        var tmp = interface.printSetPlayer(1)
        tmp should include(Console.GREEN)
        tmp = interface.printSetPlayer(2)
        tmp should include(Console.CYAN)
      }

      "addShips" in {
        // can't be tested
      }

      "printWelcome" in {
        val tmp = interface.printWelcome(Creator("Marcel"), Creator("Matthias"))
        tmp should startWith("#")
        tmp should include("Marcel")
        tmp should include("Matthias")
        tmp should include("Battleship")
        tmp should endWith("\n")
      }

      "printGetPlayer" in {
        val tmp = interface.printGetPlayer(Player("Marcel"), Player("Matthias"))
        tmp should startWith("Actual")
        tmp should include("Marcel")
        tmp should include("Matthias")
        tmp should endWith(Console.RESET)
      }

    }
  }

}