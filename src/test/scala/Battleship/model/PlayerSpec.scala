package Battleship.model

import Battleship.GameModule
import Battleship.model.Person.InterfacePerson
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}


class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val injector = Guice.createInjector(new GameModule)
      val player = injector.getInstance(classOf[InterfacePerson])

      "have a name" in {
        player.addName("Your Name")
        player.toString should be("Your Name")
      }

      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
}