package Battleship.controller

import Battleship.GameModule
import Battleship.model.fileIoComponent.fileIoJsonImpl.FileIO
import Battleship.model.gridComponent.InterfaceGrid
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val injector = Guice.createInjector(new GameModule)
      val controller = injector.getInstance(classOf[InterfaceController])

      "init Creators" in {
        controller.init()
        controller.getCreator1.toString should be("Marcel Gaiser")
        controller.getCreator2.toString should be("Matthias Reichenbach")
      }

      "players" in {
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.setPlayers("Marcel")
        controller.getPlayer1.toString should be("Marcel")
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        controller.setPlayers("Matthias")
        controller.getPlayer2.toString should be("Matthias")
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.setPlayers("")
        controller.getPlayer1.toString should be("player_01")
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        controller.setPlayers("")
        controller.getPlayer2.toString should be("player_02")
      }

      "grid" in {
        controller.getGridPlayer1.setField(0, 0, 1)
        controller.getGridPlayer1.getValue(0, 0) should be(1)
        controller.getGridPlayer2.setField(0, 0, 1)
        controller.getGridPlayer2.getValue(0, 0) should be(1)
      }

      "playerNr" in {
        controller.setWholeNrPlayer1(Array(1, 1, 1, 1))
        controller.getNrPlayer1() should be(Array(1, 1, 1, 1))
        controller.setWholeNrPlayer2(Array(1, 1, 1, 1))
        controller.getNrPlayer2() should be(Array(1, 1, 1, 1))
        controller.setNrPlayer1(1, -1)
        controller.getNrPlayer1()(1) should be(0)
        controller.setNrPlayer2(1, -1)
        controller.getNrPlayer2()(1) should be(0)

      }

      "playerState" in {
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        controller.getPlayerState should be(PlayerState.PLAYER_TWO)
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.getPlayerState should be(PlayerState.PLAYER_ONE)
      }

      "gameState" in {
        controller.setGameState(GameState.IDLE)
        controller.getGameState should be(GameState.IDLE)
        controller.setGameState(GameState.SHIPSETTING)
        controller.getGameState should be(GameState.SHIPSETTING)
      }

      "ships" in {
        controller.setShipSet(true)
        controller.getShipSet should be(true)
        controller.setShipDelete(true)
        controller.getShipDelete should be(true)
        controller.createShip()
        controller.setShips()
        controller.getShip.getSize should be(1)
        controller.deleteShip()
      }

      "checkShipSetting" in {
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.checkShipSetting("1 1 1 1") should be(false)
        controller.setPlayerState(PlayerState.PLAYER_TWO)
        controller.checkShipSetting("1 1 1 1") should be(false)
        controller.checkShipSetting("1 1 1 a") should be(false)
        controller.checkShipSetting("0 0 0 0 0") should be(false)
        controller.checkShipSetting("0 0 0 1") should be(true)
        controller.checkShipSetting("1 1 2 1") should be(true)
        controller.checkShipSetting("1 4 1 3") should be(true)
        controller.checkShipSetting("4 1 3 1") should be(true)
      }

      "lastGuess" in {
        controller.setLastGuess("1 1")
        controller.getLastGuess() should be("1 1")
        controller.checkGuess("1 1", injector.getInstance(classOf[InterfaceGrid]))
        controller.undoGuess("1 1", injector.getInstance(classOf[InterfaceGrid]))
      }

      "load and save" in {
        controller.save()
        controller.load()
        val fileIO: FileIO = new FileIO(controller.getPlayer1, controller.getPlayer2)
        fileIO.save(controller.getGridPlayer1, controller.getGridPlayer2, controller.getPlayer1, controller.getPlayer2, controller.getNrPlayer1(), controller.getNrPlayer2(), controller.getShip, Array(0, 0, 0, 0), controller.getShipSet, controller.getShipDelete, controller.getLastGuess(), controller.getGameState, controller.getPlayerState)
        fileIO.load
      }
    }
  }
}