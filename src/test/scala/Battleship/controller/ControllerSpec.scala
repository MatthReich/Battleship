package Battleship.controller

import Battleship.model.Grid
import Battleship.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(Grid(10), Grid(10))
      val observer: Observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update(): Boolean = {
          updated = true
          updated
        }
      }
      controller.add(observer)

      "notify its Observer after addShips" in {
        controller.addShips(0, Array[Int](1, 1, 2, 1))
        controller.addShips(1, Array[Int](1, 1, 2, 1))
        observer.update should be(true)
      }

      "gridToString" in {
        var tmp = controller.gridToString(0, true)
        tmp = controller.gridToString(0, false)
        tmp = controller.gridToString(1, true)
        tmp = controller.gridToString(1, false)
      }

      "checkShipSetting" in {
        var tmp = controller.checkShipSetting("1 1 1 1")
        tmp should be(Array[Int](1, 1, 1, 1))
        tmp = controller.checkShipSetting("1 1 1 a")
        tmp should be(Array[Int](1, 1, 1, 0))
      }

      "checkGuess" in {
        var tmp = controller.checkGuess("0 0", controller.grid_player01);
        tmp should be(PlayerStatus.PLAYER_TWO)
        tmp = controller.checkGuess("1 1", controller.grid_player02);
        tmp should be(PlayerStatus.PLAYER_ONE)
        tmp = controller.checkGuess("a b c", controller.grid_player01);
        tmp should be(PlayerStatus.PLAYER_ONE)
        controller.grid_player01.setField(1, 1, 2)
        tmp = controller.checkGuess("1 1", controller.grid_player01);
        tmp should be(PlayerStatus.PLAYER_TWO)
      }

    }

  }
}