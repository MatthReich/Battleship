package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {
  "Methods" when {
    "new" should {
      val creator = Creator("Your Name")

      "have a name" in {
        creator.name should be("Your Name")
      }

      "have a nice String representation" in {
        creator.toString should be("Your Name")
      }
    }
  }
}
