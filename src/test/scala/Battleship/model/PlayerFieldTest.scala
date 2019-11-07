package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}


@RunWith(classOf[JUnitRunner])
class PlayerFieldTest extends WordSpec with Matchers {

  "A PlayerField" when {
    "new" should {

      val player = Player("Your Name")
      val size = 10
      val fieldP_01 = PlayerField(player, size)

      "replaceEntry" in {
        fieldP_01.replaceEntry(0, 0, true)
        fieldP_01.listA0(0) should be(true)

        fieldP_01.replaceEntry(0, 0, false)
        fieldP_01.listA0(0) should be(false)
      }

      "getEntry" in {
        fieldP_01.getEntry(0, 0) should be(fieldP_01.listA0(0))
        fieldP_01.getEntry(9, 9) should be(fieldP_01.listA0(0))

      }

      "getSize" in {
        var sizeTest = fieldP_01.getSize()
        sizeTest should be (size)
      }

      "printField" in {
        val string = fieldP_01.printField(1)
        string should startWith("Field of: ")
        string should endWith("\n")
        string should include("A0")
        string should include("A5")
        string should include("A9")
      }

      "printField error" in {
        val string = fieldP_01.printField(3)
        string should be ("something went wrong...\n")
      }

      "check ship placement" in {
        val boolTest = fieldP_01.checkShipplacementRules()
        boolTest should be (false)
      }
    }
  }
}
