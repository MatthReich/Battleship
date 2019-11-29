package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.Grid
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {

  "A Tui" when {
    "new" should {
      var grid_1 = new Grid(10);
      var grid_2 = new Grid(10);
      val tui = new Tui(new Controller(grid_1, grid_2))
      "printGrid" in {
        tui.printGrid(0)
      }

      "printShipSetSettings" in {
        tui.printShipSetSettings(Array[Int](1, 0, 0, 0))
      }

      "shipProcess" in {
        tui.shipProcess("0 0 0 1", 0)
      }

      "printFirstTimeProcessLine" in {
        tui.printFirstTimeProcessLine()
      }

      "processLine" in {
        tui.processLine("q")
        tui.processLine("getPlayerConfig")
        tui.processLine("getGameStatus")
        tui.processLine("0 0")
        tui.processLine("0 0")
      }

      "update" in {
        tui.shipProcess = true
        tui.update
      }

    }
  }

}
