package Battleship.aview

import Battleship.controller.Controller
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {

  "A Tui" when {
    "new" should {
      val controller = new Controller(Grid(10), Grid(10))
      val tui = new Tui(controller)

      "printGrid" in {
        tui.printGrid(0)
      }

      "printShipSetSettings" in {
        tui.printShipSetSettings(Array[Int](1, 0, 0, 0))
      }

      "shipProcessLong" in {
        tui.shipProcessLong("delete Ship")
        tui.shipProcessLong(" ")
      }

      "shipProcess" in {
        tui.shipProcess("0 0 0 1")
        tui.shipProcess("a")
      }

      "decreaseShipNumbersToPlace" in {

        val boolean: Boolean = true
        val shipLength2: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
        val shipLength3: Ship = Ship(Array(0, 0, 0, 2), new StrategyCollideNormal)
        val shipLength4: Ship = Ship(Array(0, 0, 0, 3), new StrategyCollideNormal)
        val shipLength5: Ship = Ship(Array(0, 0, 0, 4), new StrategyCollideNormal)

        controller.nr = Array(1, 1, 1, 1)
        controller.nr2 = Array(1, 1, 1, 1)
        /*
                tui.decreaseShipNumbersToPlace(shipLength2, boolean)
                tui.decreaseShipNumbersToPlace(shipLength3, boolean)
                tui.decreaseShipNumbersToPlace(shipLength4, boolean)
                tui.decreaseShipNumbersToPlace(shipLength5, boolean)

                controller.playerState = PlayerState.PLAYER_TWO

                tui.decreaseShipNumbersToPlace(shipLength3, boolean = false)
                tui.decreaseShipNumbersToPlace(shipLength2, boolean)
                tui.decreaseShipNumbersToPlace(shipLength3, boolean)
                tui.decreaseShipNumbersToPlace(shipLength4, boolean)
                tui.decreaseShipNumbersToPlace(shipLength5, boolean)
        */
      }

      "printFirstTimeProcessLine" in {
        tui.printGridOption = true
        tui.shipProcess = true
        tui.printFirstTimeProcessLine()
        tui.printGridOption = false
        tui.shipProcess = false
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

        val controller = new Controller(Grid(1), Grid(1))
        controller.grid_player01.setField(0, 0, 2)
        tui.update
        controller.grid_player01.setField(0, 0, 0)
        tui.update
      }

    }
  }

}
