/*package Battleship.controller

import Battleship.GameModule
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val injector = Guice.createInjector(new GameModule)
      val controller = new Controller()
      controller.init()
      "createShip" in {
        controller.shipCoordsSetting = Array(0, 0, 0, 2)
        controller.createShip()
        controller.playerState = PlayerState.PLAYER_TWO
        controller.createShip()
        controller.ship.getSize should be(3)
      }

      "setShip" in {
        controller.setShips()
        controller.playerState = PlayerState.PLAYER_ONE
        controller.setShips()
      }

      "gridToString" in {
        var tmp = controller.gridToString(0, boolean = true)
        tmp = controller.gridToString(0, boolean = false)
        tmp = controller.gridToString(1, boolean = true)
        tmp = controller.gridToString(1, boolean = false)
      }

      "shipToString" in {
        val ship: InterfaceShip = injector.getInstance(classOf[InterfaceShip])
        ship.setCoordinates(Array(0, 0, 0, 1))
        controller.shipToString(ship) should be("0 0 \n0 1 \n")
      }

      "checkShipSetting" in {
        controller.playerState = PlayerState.PLAYER_ONE
        controller.checkShipSetting("1 1 1 1") should be(false)
        controller.playerState = PlayerState.PLAYER_TWO
        controller.checkShipSetting("1 1 1 1") should be(false)
        controller.checkShipSetting("1 1 1 a") should be(false)
        controller.checkShipSetting("0 0 0 0 0") should be(false)
        controller.checkShipSetting("0 0 0 1") should be(true)
        controller.checkShipSetting("1 1 2 1") should be(true)
        controller.checkShipSetting("1 4 1 3") should be(true)
        controller.checkShipSetting("4 1 3 1") should be(true)
      }

      "checkGuess" in {
        controller.checkGuess("1 1", injector.getInstance(classOf[InterfaceGrid]))
      }

      "undoGuess" in {
        controller.undoGuess("1 1", injector.getInstance(classOf[InterfaceGrid]))
      }

      "setLastGuess" in {
        controller.setLastGuess("1 1")
        controller.lastGuess should be("1 1")
      }

      "deleteShip" in {
        controller.deleteShip()
      }

      "setPlayers" in {
        controller.playerState = PlayerState.PLAYER_ONE
        controller.setPlayers("Marcel")
        controller.playerState = PlayerState.PLAYER_TWO
        controller.setPlayers("Matthias")
        controller.playerState = PlayerState.PLAYER_ONE
        controller.setPlayers("")
        controller.playerState = PlayerState.PLAYER_TWO
        controller.setPlayers("")
      }

      "checkState" in {
        var state = GameState.message(GameState.IDLE)
        state should be("")
        state = PlayerState.message(PlayerState.PLAYER_ONE)
        state should be("player_01's turn")
      }

      "getGridPlayer1" in {
        val grid = controller.getGridPlayer1
        grid should be(controller.grid_player01)
      }

      "getGridPlayer2" in {
        val grid = controller.getGridPlayer2
        grid should be(controller.grid_player02)
      }

      "getPlayerState" in {
        val playerSt = controller.getPlayerState
        playerSt should be(controller.playerState)
      }

      "getCreator1" in {
        val creator = controller.getCreator1
        creator should be(controller.creator_01)
      }

      "getCreator2" in {
        val creator = controller.getCreator2
        creator should be(controller.creator_02)
      }

      "getPlayer1" in {
        val player = controller.getPlayer1
        player should be(controller.player_01)
      }

      "getPlayer2" in {
        val player = controller.getPlayer2
        player should be(controller.player_02)
      }

      "getGameState" in {
        val gameSt = controller.getGameState
        gameSt should be(controller.gameState)
      }

      "getShipSet" in {
        val ship = controller.getShipSet
        ship should be(controller.shipSet)
      }

      "getShipDetelte" in {
        val ship = controller.getShipDelete
        ship should be(controller.shipDelete)
      }

      "getShip" in {
        val ship = controller.getShip
        ship should be(controller.ship)
      }

      "setGameState" in {
        controller.setGameState(GameState.SHIPSETTING)
        controller.gameState should be(GameState.SHIPSETTING)
      }

      "shipSet" in {
        controller.shipSet(true)
        controller.shipSet should be(true)
      }

      "setNrPlayer1" in {
        controller.setNrPlayer1(0, 1)
        controller.nr(0) should be(3)
      }

      "setNrPlayer2" in {
        controller.setNrPlayer2(0, 1)
        controller.nr2(0) should be(2)
      }

    }

  }
}*/