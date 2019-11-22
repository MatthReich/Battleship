package Battleship.TUI


import Battleship.model.{Creator, Grid, Player}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TUIMethodsTest extends WordSpec with Matchers {

  "printSetPlayer" in {
    var tmp = TUIMethods.printSetPlayer(1)
    tmp should include(Console.GREEN)
    tmp = TUIMethods.printSetPlayer(2)
    tmp should include(Console.CYAN)
  }

  "addShips" in {
    // can't be tested
  }

  "checkValidShip" in {
    var tmp = TUIMethods.checkValidShip("1 1 2 1")
    tmp should be(Array[Int](1, 1, 2, 1))
    tmp = TUIMethods.checkValidShip("1 1 2 1 2")
    tmp(0) should be(10)
    tmp = TUIMethods.checkValidShip("a 1 2 1")
    tmp(0) should be(10)
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

  "printGrid while setting" in {
    val grid: Grid = Grid(10)
    grid.setField(1, 1, 1)
    grid.setField(2, 2, 2)
    grid.setField(3, 3, 3)

    val tmp = TUIMethods.printGrid(grid, Player("Marcel"), sortOfPrint = true)

    tmp should startWith("Field of:")
    tmp should include("Marcel")
    tmp should include("A")
    tmp should include("~")
    tmp should include(Console.GREEN + "  x  " + Console.RESET)
    tmp should include(Console.RED + "  x  " + Console.RESET)
    tmp should include("0")
    tmp should endWith("\n")

  }

  "printGrid while in match" in {
    val grid2: Grid = Grid(10)
    grid2.setField(0, 0, 1)
    grid2.setField(2, 2, 2)
    grid2.setField(0, 1, 3)

    val tmp2 = TUIMethods.printGrid(grid2, Player("Marcel"), sortOfPrint = false)

    tmp2 should startWith("Field of:")
    tmp2 should include("Marcel")
    tmp2 should include("A")
    tmp2 should include("~")
    (tmp2 should not).include(Console.GREEN + "  x  " + Console.RESET)
    tmp2 should include(Console.RED + "  x  " + Console.RESET)
    tmp2 should include("0")
    tmp2 should endWith("\n")
  }

}