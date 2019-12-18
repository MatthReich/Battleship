package Battleship.model

import Battleship.model.Person.Player
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}


@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")

    "have a name"  in {
      player.name should be("Your Name")
    }

    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}
}