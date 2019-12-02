package Battleship.TUI

import Battleship.controller.Controller
import Battleship.model.Grid
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {

  "A Tui" when {
    "new" should {
      val grid_1 = Grid(10)
      val grid_2 = Grid(10)
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
        tui.printGridOption = true
        tui.printFirstTimeProcessLine()
      }

      "processLine" in {
        tui.processLine("q")
        tui.processLine("getPlayerConfig")
        tui.processLine("getGameStatus")
        tui.processLine("getPlayerStatus")
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
