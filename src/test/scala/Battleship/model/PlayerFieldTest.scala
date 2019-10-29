package Battleship.model

import org.scalatest.{Matchers, WordSpec}

class PlayerFieldTest extends WordSpec with Matchers {

  "A PlayerField" when {
    "new" should {

    val player = Player("Your Name")
    val size = 10
    val fieldP_01 = PlayerField(player, size)

      "contains one Of" in {

      }

    "replaceEntry" in {
        fieldP_01.replaceEntry(0,0,true)
    }

      "printField" in {
        val string = fieldP_01.printField()
        string should startWith ("Field of: " + player)
        string should endWith ("END OF FIELD\n")
     }
  }}
}
