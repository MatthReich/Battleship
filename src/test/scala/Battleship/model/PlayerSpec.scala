package Battleship.model

import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
      player.toString should be("Your Name")
    }
    "have a nice String representation" in {
      Matchers.convertToStringShouldWrapper(player.toString) should be("Your Name")
    }
  }}
}