package Battleship.aview

import Battleship.controller.{Controller, GameStatus, PlayerStatus}
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
        tui.shipProcess("0 0 0 1")
      }

      "decreaseShipNumbersToPlace" in {
        val controller = new Controller(Grid(10), Grid(10))

        val boolean: Boolean = true
        val shipLenght2: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
        val shipLenght3: Ship = Ship(Array(0, 0, 0, 2), new StrategyCollideNormal)
        val shipLenght4: Ship = Ship(Array(0, 0, 0, 3), new StrategyCollideNormal)
        val shipLenght5: Ship = Ship(Array(0, 0, 0, 4), new StrategyCollideNormal)

        controller.nr = Array(1, 1, 1, 1)
        controller.nr2 = Array(1, 1, 1, 1)

        tui.decreaseShipNumbersToPlace(shipLenght2, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght3, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght4, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght5, boolean)

        controller.playerStatus = PlayerStatus.PLAYER_TWO

        tui.decreaseShipNumbersToPlace(shipLenght2, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght3, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght4, boolean)
        tui.decreaseShipNumbersToPlace(shipLenght5, boolean)
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

        val controller = new Controller(Grid(1), Grid(1))
        controller.grid_player01.setField(0, 0, 2)
        tui.update
      }

    }
  }

}
