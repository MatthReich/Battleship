package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.{Grid, Player}
import org.scalatest.{Matchers, WordSpec}

class TUIInterfaceTest extends WordSpec with Matchers {

  "A TUIInterface" when {
    "new" should {
      val interface: TUIInterface = new TUIInterface(new Controller())

      "playerConfiguration" in {
        interface.playerConfiguration()
      }

      "setShipsPrint" in {
        interface.setShipsPrint(Player("Marcel"), Grid(10), Array[Int](1, 1, 1, 1))
      }

    }
  }

}
