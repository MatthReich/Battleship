/*package Battleship.controller

import Battleship.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
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
        controller.addShips(controller.playerGrid_01, Array[Int](1, 1, 2, 1), Array[Int](1, 1, 1, 1))
        controller.playerGrid_01.getField(1, 1) should be(1)
        observer.update should be(true)
      }
    }

  }
}
 */