package Battleship.model

import Battleship.model.Person.Creator
import org.scalatest.{Matchers, WordSpec}

class CreatorSpec extends WordSpec with Matchers {
  "A Creator" when { "new" should {
    val creator = Creator("Your Name")

    "have a name" in {
      creator.name should be("Your Name")
    }

    "have a nice String representation" in {
      creator.toString should be("Your Name")
    }
  }}
}