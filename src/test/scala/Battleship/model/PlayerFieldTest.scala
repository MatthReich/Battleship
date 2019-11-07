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

      //noinspection ScalaStyle
      "replaceEntry" in {
        fieldP_01.replaceEntry(0, 0, true)
        fieldP_01.listA0(0) should be(true)
        fieldP_01.replaceEntry(1, 0, true)
        fieldP_01.listA1(0) should be (true)
        fieldP_01.replaceEntry(2, 0, true)
        fieldP_01.listA2(0) should be(true)
        fieldP_01.replaceEntry(3, 0, true)
        fieldP_01.listA3(0) should be(true)
        fieldP_01.replaceEntry(4, 0, true)
        fieldP_01.listA4(0) should be(true)
        fieldP_01.replaceEntry(5, 0, true)
        fieldP_01.listA5(0) should be(true)
        fieldP_01.replaceEntry(6, 0, true)
        fieldP_01.listA6(0) should be(true)
        fieldP_01.replaceEntry(7, 0, true)
        fieldP_01.listA7(0) should be(true)
        fieldP_01.replaceEntry(8, 0, true)
        fieldP_01.listA8(0) should be(true)
        fieldP_01.replaceEntry(9, 0, true)
        fieldP_01.listA9(0) should be(true)

        fieldP_01.replaceEntry(0, 0,  false)
        fieldP_01.listA0(0) should be (false)
        fieldP_01.replaceEntry(1, 0,  false)
        fieldP_01.listA1(0) should be (false)
        fieldP_01.replaceEntry(2, 0,  false)
        fieldP_01.listA2(0) should be (false)
        fieldP_01.replaceEntry(3, 0,  false)
        fieldP_01.listA3(0) should be (false)
        fieldP_01.replaceEntry(4, 0,  false)
        fieldP_01.listA4(0) should be (false)
        fieldP_01.replaceEntry(5, 0,  false)
        fieldP_01.listA5(0) should be (false)
        fieldP_01.replaceEntry(6, 0,  false)
        fieldP_01.listA6(0) should be (false)
        fieldP_01.replaceEntry(7, 0,  false)
        fieldP_01.listA7(0) should be (false)
        fieldP_01.replaceEntry(8, 0,  false)
        fieldP_01.listA8(0) should be (false)
        fieldP_01.replaceEntry(9, 0,  false)
        fieldP_01.listA9(0) should be (false)
      }

      //noinspection ScalaStyle
      "getEntry" in {
        fieldP_01.getEntry(0, 0) should be(fieldP_01.listA0(0))
        fieldP_01.getEntry(1, 0) should be(fieldP_01.listA1(0))
        fieldP_01.getEntry(2, 0) should be(fieldP_01.listA2(0))
        fieldP_01.getEntry(3, 0) should be(fieldP_01.listA3(0))
        fieldP_01.getEntry(4, 0) should be(fieldP_01.listA4(0))
        fieldP_01.getEntry(5, 0) should be(fieldP_01.listA5(0))
        fieldP_01.getEntry(6, 0) should be(fieldP_01.listA6(0))
        fieldP_01.getEntry(7, 0) should be(fieldP_01.listA7(0))
        fieldP_01.getEntry(8, 0) should be(fieldP_01.listA8(0))
        fieldP_01.getEntry(9, 0) should be(fieldP_01.listA9(0))
      }

      "getSize" in {
        val sizeTest = fieldP_01.getSize()
        sizeTest should be (size)
      }

      "printField for player 1" in {
        val string = fieldP_01.printField(1)
        string should startWith("Field of: ")
        string should endWith("\n")
        string should include("A0")
        string should include("A5")
        string should include("A9")
      }

      "printField for player 2" in {
        val string = fieldP_01.printField(2)
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
