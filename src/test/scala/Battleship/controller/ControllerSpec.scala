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

      "notify its Observer after addShips" in {
        var tmp = controller.addShips("1 1 1 1")
        tmp should be(Array(1, 1, 1, 1))
        tmp = controller.addShips("1 1 1 1 1")
        tmp(0) should be(10)
        tmp = controller.addShips("a 1 1 1")
        tmp(0) should be(10)
        observer.updated should be(true)
      }
    }

  }
}