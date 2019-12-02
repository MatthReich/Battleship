package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.Grid
import org.scalatest.{Matchers, WordSpec}

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

    }
  }

}