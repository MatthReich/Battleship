package Battleship.controller

import Battleship.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Unit = updated = true
      }
      controller.add(observer)

      "notify its Observer after printWelcome" in {
        controller.printWelcome()
        observer.updated should be(true)
      }

      "notify its Observer after printPlayer" in {
        controller.printPlayer()
        observer.updated should be(true)
      }

      "notify its Observer after printGrid" in {
        controller.printGrid()
        observer.updated should be(true)
      }
    }
  }
}
