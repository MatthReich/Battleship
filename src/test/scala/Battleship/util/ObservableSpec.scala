package Battleship.util

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ObservableSpec extends WordSpec with Matchers {
  "An Observable" should {
    val observable = new Observable
    val observer: Observer = new Observer {
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      override def update: Boolean = {
        updated = true
        updated
      }
    }

    "add an Observer" in {
      observable.add(observer)
      observable.subscribers should contain(observer)
    }
    "notify an Observer" in {
      observable.notifyObservers()
      observer.update should be(true)
    }
    "remove an Observer" in {
      observable.remove(observer)
      observable.subscribers should not contain observer
    }
  }
}
