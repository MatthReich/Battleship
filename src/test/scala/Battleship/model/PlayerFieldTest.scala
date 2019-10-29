package Battleship.model

import org.scalatest.{Matchers, WordSpec}

class PlayerFieldTest extends WordSpec with Matchers {

  "A PlayerField" when {
    "new" should {

      val player = Player("Your Name")
      val size = 10
      val fieldP_01 = PlayerField(player, size)

      "get coverage" in {
        PlayerField.unapply(fieldP_01).get should be (("Your Name", 10))
      }

      "replaceEntry" in {
        fieldP_01.replaceEntry(0, 0, true)
        fieldP_01.listA0(0) should be(true)

        fieldP_01.replaceEntry(0, 0, false)
        fieldP_01.listA0(0) should be(false)
      }

      "getEntry" in {
        fieldP_01.getEntry(0, 0) should be(fieldP_01.listA0(0))
      }

      "printField" in {
        val string = fieldP_01.printField(1)
        string should startWith("Field of: " + player)
        string should endWith("\n")
        string should include("A0")
        string should include("A5")
        string should include("A9")
      }
    }
  }
}
