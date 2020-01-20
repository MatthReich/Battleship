package Battleship.model

import Battleship.model.Person.{Creator, Player}
import org.scalatest.{Matchers, WordSpec}

class PersonSpec extends WordSpec with Matchers {
  "A Person" when {
    "new" should {
      val player = new Player
      val creator = new Creator

      "player have a name" in {
        player.addName("Your Name")
        player.toString should be("Your Name")
      }

      "creator have a name" in {
        creator.addName("Your Name")
        creator.toString should be("Your Name")
      }
    }
  }
}
