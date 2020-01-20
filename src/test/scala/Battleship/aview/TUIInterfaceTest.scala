package Battleship.aview

import Battleship.GameModule
import Battleship.aview.TUI.TUIInterface
import Battleship.controller.InterfaceController
import Battleship.model.Person.InterfacePerson
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class TUIInterfaceTest extends WordSpec with Matchers {

  val injector = Guice.createInjector(new GameModule)
  val controller: InterfaceController = injector.getInstance(classOf[InterfaceController])
  controller.init()
  val interface = new TUIInterface(controller)

  "A TUIInterface" when {
    "new" should {

      "update" in {
        val tmp = interface.update
        tmp should be(true)
      }

      "playerConfiguration" in {
        interface.playerConfiguration()
      }

      "printWelcomeX" in {
        interface.printWelcomeX()
      }

      "printSetPlayer" in {
        var tmp = interface.printSetPlayer(1)
        tmp should include(Console.GREEN)
        tmp = interface.printSetPlayer(2)
        tmp should include(Console.CYAN)
      }

      "printWelcome" in {
        val creator1: InterfacePerson = injector.getInstance(classOf[InterfacePerson])
        creator1.addName("Marcel")
        val creator2: InterfacePerson = injector.getInstance(classOf[InterfacePerson])
        creator2.addName("Matthias")
        val tmp = interface.printWelcome(creator1, creator2)
        tmp should startWith("#")
        tmp should include("Marcel")
        tmp should include("Matthias")
        tmp should include("Battleship")
        tmp should endWith("\n")
      }

      "printGetPlayer" in {
        val player1: InterfacePerson = injector.getInstance(classOf[InterfacePerson])
        player1.addName("Marcel")
        val player2: InterfacePerson = injector.getInstance(classOf[InterfacePerson])
        player2.addName("Matthias")
        val tmp = interface.printGetPlayer(player1, player2)
        tmp should startWith("Actual")
        tmp should include("Marcel")
        tmp should include("Matthias")
        tmp should endWith(Console.RESET)
      }

    }
  }

}