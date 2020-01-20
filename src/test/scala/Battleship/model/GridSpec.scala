package Battleship.model

import Battleship.GameModule
import Battleship.controller.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {

  "A Grid" when {
    "new" should {
      val injector = Guice.createInjector(new GameModule)
      val grid = injector.getInstance(classOf[InterfaceGrid])
      val gridShort = injector.getInstance(classOf[InterfaceGrid])

      "getField" in {
        val tmp = grid.getField(0, 0)
        tmp should be(0)
      }

      "setField" in {
        grid.setField(0, 0, 1)
        val tmp = grid.getValue(0, 0)
        tmp should be(1)
      }

      "getValue" in {
        gridShort.setField(0, 0, 1)
        gridShort.getValue(0, 0) should be (1)
        gridShort.setField(0, 0, 0)
        gridShort.getValue(0, 0) should be(0)
      }

      "winStatement" in {
        var check = gridShort.winStatement()
        check should be(true)
        gridShort.setField(0, 0, 1)
        check = gridShort.winStatement()
        check should be(false)
      }

      "toString" in {
        val grid = injector.getInstance(classOf[InterfaceGrid])
        grid.setField(1, 1, 1)
        grid.setField(2, 2, 2)
        grid.setField(3, 3, 3)

        var person = injector.getInstance(classOf[InterfacePerson])
        person.addName("Marcel")
        var tmp = grid.toString(person, sortOfPrint = true, PlayerState.PLAYER_ONE)

        tmp should startWith("Field of:")
        tmp should include("Marcel")
        tmp should include("A")
        tmp should include("~")
        tmp should include(Console.GREEN + "  x  " + Console.RESET)
        tmp should include(Console.RED + "  x  " + Console.RESET)
        tmp should include("0")
        tmp should endWith("\n")
        person.addName("Matthias")
        tmp = grid.toString(person, sortOfPrint = false, PlayerState.PLAYER_TWO)

        tmp should startWith("Field of:")
        tmp should include("Matthias")
        tmp should include("A")
        tmp should include("~")
        tmp should include(Console.BLUE + "  ~  " + Console.RESET)
        tmp should include(Console.RED + "  x  " + Console.RESET)
        tmp should include("0")
        tmp should endWith("\n")
      }

      "getSize" in {
        grid.getSize should be(grid.getSize)
      }

    }
  }

}
