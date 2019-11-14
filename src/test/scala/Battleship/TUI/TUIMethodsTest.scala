package Battleship.TUI

import Battleship.model.{Creator, Grid, Player}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {

  "setPlayer" in {
    // can't be tested
  }

  "addShips" in {
    // can't be tested
  }

  "printWelcome" in {
    val tmp = TUIMethods.printWelcome(Creator("Marcel"), Creator("Matthias"))
    tmp should startWith("#")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should include("Battleship")
    tmp should endWith("\n")
  }

  "printGetPlayer" in {
    val tmp = TUIMethods.printGetPlayer(Player("Marcel"), Player("Matthias"))
    tmp should startWith("Actual")
    tmp should include("Marcel")
    tmp should include("Matthias")
    tmp should endWith(Console.RESET)
  }

  "printNrOfShips" in {
    val list: Array[Int] = Array(0, 2, 5, 0)
    val tmp = TUIMethods.printNrOfShips(list)
    tmp should startWith("Please")
    tmp should include("0")
    tmp should include("2")
    tmp should include("5")
    tmp should endWith("\n")
  }

  "printGrid" in {
    var grid: Grid = Grid(10)
    grid.setField(1, 1, 1)
    grid.setField(2, 2, 2)
    grid.setField(3, 3, 3)
    val tmp = TUIMethods.printGrid(grid, Player("Marcel"))
    tmp should startWith("Field of:")
    tmp should include("Marcel")
    tmp should include("A")
    tmp should include("~")
    tmp should include("x")
    tmp should include("0")
    tmp should endWith("\n")
  }

}
