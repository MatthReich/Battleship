package Battleship.model

import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")

    "get coverage" in {
      Player.unapply(player).get should be ("Your Name")
    }

    "have a name"  in {
      player.name should be("Your Name")
    }

    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}
}