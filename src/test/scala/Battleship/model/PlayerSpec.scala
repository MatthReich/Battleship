package Battleship.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}


@RunWith(classOf[JUnitRunner])
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