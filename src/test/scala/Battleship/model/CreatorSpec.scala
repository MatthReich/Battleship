package Battleship.model

import Battleship.GameModule
import Battleship.model.Person.InterfacePerson
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class CreatorSpec extends WordSpec with Matchers {

  "A Creator" when {
    "new" should {
      val injector = Guice.createInjector(new GameModule)
      val creator = injector.getInstance(classOf[InterfacePerson])

      "have a name" in {
        creator.addName("Your Name")
        creator.toString should be("Your Name")
      }

      "have a nice String representation" in {
        creator.toString should be("Your Name")
      }
    }
  }
}