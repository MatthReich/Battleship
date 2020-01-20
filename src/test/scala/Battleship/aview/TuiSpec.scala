package Battleship.aview

import Battleship.GameModule
import Battleship.aview.TUI.Tui
import Battleship.controller.{GameState, InterfaceController, PlayerState}
import Battleship.model.shipComponent.InterfaceShip
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {

  "A Tui" when {
    "new" should {
      val injector = Guice.createInjector(new GameModule)
      val controller: InterfaceController = injector.getInstance(classOf[InterfaceController])
      controller.init()
      val tui = new Tui(controller)

      "printGrid" in {
        tui.printGrid(controller.getGridPlayer1, controller.getPlayer1)
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
        val shipLength2: InterfaceShip = injector.getInstance(classOf[InterfaceShip])
        shipLength2.setCoordinates(Array(0, 0, 0, 1))
        val shipLength3: InterfaceShip = injector.getInstance(classOf[InterfaceShip])
        shipLength3.setCoordinates(Array(0, 0, 0, 2))
        val shipLength4: InterfaceShip = injector.getInstance(classOf[InterfaceShip])
        shipLength4.setCoordinates(Array(0, 0, 0, 3))
        val shipLength5: InterfaceShip = injector.getInstance(classOf[InterfaceShip])
        shipLength5.setCoordinates(Array(0, 0, 0, 4))

        controller.setWholeNrPlayer1(Array(1, 1, 1, 1))
        controller.setWholeNrPlayer2(Array(1, 1, 1, 1))
        tui.decreaseShipNumbersToPlace(shipLength2, boolean, true)
        tui.decreaseShipNumbersToPlace(shipLength3, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength4, boolean, false)
        tui.decreaseShipNumbersToPlace(shipLength5, boolean, false)

        controller.setPlayerState(PlayerState.PLAYER_TWO)

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
        tui.processLine("getGameState")
        tui.processLine("getPlayerState")
        tui.processLine("admin: printGrid 1")
        tui.processLine("admin: printGrid 2")
        controller.setGameState(GameState.PLAYERSETTING)
        tui.processLine("Name")
        controller.setGameState(GameState.SHIPSETTING)
        tui.processLine("0 0 1 0")
        controller.setGameState(GameState.IDLE)
        tui.processLine("0 0")
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.setGameState(GameState.IDLE)
        tui.processLine("undo Guess")
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        controller.setGameState(GameState.IDLE)
        tui.processLine("undo Guess")
        controller.setGameState(GameState.IDLE)
        tui.processLine("0 0")
        tui.processLine("save")
        tui.processLine("load")
      }

      "update" in {
        tui.shipProcess = true
        tui.update

        val controller = injector.getInstance(classOf[InterfaceController])
        controller.init()
        controller.getGridPlayer1.setField(0, 0, 2)
        tui.update
        controller.getGridPlayer2.setField(0, 0, 0)
        tui.update
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        tui.update
      }

    }
  }

}