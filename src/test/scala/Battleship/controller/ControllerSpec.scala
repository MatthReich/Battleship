package Battleship.controller

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(Grid(10), Grid(10))
      "createShip" in {
        controller.shipCoordsSetting = Array(0, 0, 0, 2)
        controller.createShip()
        controller.playerState = PlayerState.PLAYER_TWO
        controller.createShip()
        controller.ship.getSize should be (3)
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
        val ship: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
        controller.shipToString(ship) should be ("0 0 \n0 1 \n")
      }

      "checkShipSetting" in {
        controller.checkShipSetting("1 1 1 1") should be (false)
        controller.checkShipSetting("1 1 1 a") should be (false)
        controller.checkShipSetting("0 0 0 0 0") should be (false)
        controller.checkShipSetting("0 0 0 1") should be (true)
      }
/*
      "checkGuess" in {
        // no hit -> one to two
        var grid = Grid(1)
        controller.playerState = PlayerState.PLAYER_ONE
        controller.playerState = controller.checkGuess("0 0", grid)
        controller.playerState should be (PlayerState.PLAYER_TWO)
        // hit -> two two
        grid.setField(0, 0, 1)
        controller.playerState = controller.checkGuess("0 0", grid)
        controller.playerState should be (PlayerState.PLAYER_TWO)
        // false input
        controller.playerState = controller.checkGuess("0 0 0", grid)
        controller.playerState should be (PlayerState.PLAYER_TWO)
        controller.playerState = controller.checkGuess("0 a", grid)
        controller.playerState should be (PlayerState.PLAYER_TWO)
        // no hit -> two to one
        grid.setField(0, 0,2)
        controller.playerState = controller.checkGuess("0 0", grid)
        controller.playerState should be (PlayerState.PLAYER_ONE)
      }
*/
      "checkState" in {
        var state = GameState.message(GameState.IDLE)
        state should be("")
        state = PlayerState.message(PlayerState.PLAYER_ONE)
        state should be("player_01's turn")
      }

    }

  }
}