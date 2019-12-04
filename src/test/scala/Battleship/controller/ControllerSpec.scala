package Battleship.controller

import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.StrategyCollideNormal
import Battleship.util.Observer
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(Grid(10), Grid(10))
      val observer: Observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Boolean = {
          updated = true
          updated
        }
      }
      controller.add(observer)
/*
      "notify its Observer after addShips" in {
        controller.addShips(0, Array[Int](1, 1, 2, 1))
        controller.addShips(1, Array[Int](1, 1, 2, 1))
        observer.update should be(true)
      }
*/
      "createShip" in {
        controller.shipCoordsSetting = Array(0, 0, 0, 2)
        controller.createShip()
        controller.playerStatus = PlayerStatus.PLAYER_TWO
        controller.createShip()
        controller.ship.getSize should be (3)
        observer.update should be (true)
      }

      "setShip" in {
        controller.setShips()
        controller.playerStatus = PlayerStatus.PLAYER_ONE
        controller.setShips()
        observer.update should be (true)
      }

      "gridToString" in {
        var tmp = controller.gridToString(0, boolean = true)
        tmp = controller.gridToString(0, boolean = false)
        tmp = controller.gridToString(1, boolean = true)
        tmp = controller.gridToString(1, boolean = false)
      }

      "shipToString" in {
        val ship: Ship = Ship(Array(0, 0, 0, 1), new StrategyCollideNormal)
        ship.toString should be ("0 0 \n0 1 \n")
      }

      "checkShipSetting" in {
        controller.checkShipSetting("1 1 1 1") should be (false)
        controller.checkShipSetting("1 1 1 a") should be (false)
        controller.checkShipSetting("0 0 0 1") should be (true)
      }
/*
      "checkGuess" in { // redo this test !! change everytime playerstatus not just tmp
        // no hit -> change one to two
        var tmp = controller.checkGuess("0 0", controller.grid_player01)
        tmp should be(PlayerStatus.PLAYER_TWO)
        // no hit -> change two to one
        tmp = controller.checkGuess("1 1", controller.grid_player02)
        tmp should be(PlayerStatus.PLAYER_ONE)
        // to long argument -> hit and repeat -> one to one
        tmp = controller.checkGuess("a b c", controller.grid_player01)
        tmp should be(PlayerStatus.PLAYER_ONE)
        // ?? -> one to two
        controller.grid_player01.setField(1, 1, 2)
        tmp = controller.checkGuess("1 1", controller.grid_player01)
        tmp should be(PlayerStatus.PLAYER_TWO)
        // right input length but no int -> two to two
        controller.playerStatus = PlayerStatus.PLAYER_TWO
        tmp = controller.checkGuess("a 1", controller.grid_player01)
        tmp should be(PlayerStatus.PLAYER_TWO)
        // no hit in player two -> two to one
        controller.grid_player01.setField(1, 1, 0)
        tmp = controller.checkGuess("1 1", controller.grid_player01)
        tmp should be(PlayerStatus.PLAYER_ONE)
      }
*/
      "checkGuess" in {
        // no hit -> one to two
        var grid = Grid(1)
        controller.playerStatus = PlayerStatus.PLAYER_ONE
        controller.playerStatus = controller.checkGuess("0 0", grid)
        controller.playerStatus should be (PlayerStatus.PLAYER_TWO)
        // hit -> two two
        grid.setField(0, 0, 1)
        controller.playerStatus = controller.checkGuess("0 0", grid)
        controller.playerStatus should be (PlayerStatus.PLAYER_TWO)
        // false input
        controller.playerStatus = controller.checkGuess("0 0 0", grid)
        controller.playerStatus should be (PlayerStatus.PLAYER_TWO)
        controller.playerStatus = controller.checkGuess("0 a", grid)
        controller.playerStatus should be (PlayerStatus.PLAYER_TWO)
        // no hit -> two to one
        grid.setField(0, 0,0)
        controller.playerStatus = controller.checkGuess("0 0", grid)
        controller.playerStatus should be (PlayerStatus.PLAYER_ONE)
      }

      "checkState" in {
        var state = GameStatus.message(GameStatus.IDLE)
        state should be("")
        state = PlayerStatus.message(PlayerStatus.PLAYER_ONE)
        state should be("player_01's turn")
      }

    }

  }
}