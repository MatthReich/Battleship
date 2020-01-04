package Battleship.aview

import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
/*
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
        tui.decreaseShipNumbersToPlace(shipLength2, boolean, true)
        tui.decreaseShipNumbersToPlace(shipLength3, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength4, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength5, boolean, false)

        controller.playerState = PlayerState.PLAYER_TWO

        tui.decreaseShipNumbersToPlace(shipLength3, boolean = false, false)
        tui.decreaseShipNumbersToPlace(shipLength2, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength3, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength4, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength5, boolean, false)
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
        tui.processLine("getPlayerConfig")
        tui.processLine("getGameStatus")
        tui.processLine("getPlayerStatus")
        tui.processLine("admin: printGrid 1")
        tui.processLine("admin: printGrid 2")
        controller.gameState = GameState.PLAYERSETTING
        tui.processLine("Name")
        controller.gameState = GameState.SHIPSETTING
        tui.processLine("0 0 1 0")
        controller.gameState = GameState.IDLE
        tui.processLine("0 0")
        controller.playerState = PlayerState.PLAYER_ONE
        controller.gameState = GameState.IDLE
        tui.processLine("undo Guess")
        controller.playerState = PlayerState.PLAYER_TWO
        controller.gameState = GameState.IDLE
        tui.processLine("undo Guess")
        controller.gameState = GameState.IDLE
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
        controller.playerState = PlayerState.PLAYER_TWO
        tui.update
      }

    }
  }*/

}
