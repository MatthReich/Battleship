import Battleship.model.Grid

import scala.util.Try

val string:String = "1 2"

def parseCSV(csv: String) = {
  try {
    Some {
      csv.split("\n").map { entry =>
        val token = entry.split(" ")
        val x = token(0).toInt
        val y = token(1).toInt
        print(x + " + " + y)
      }
    }
  } catch {
    case _: NumberFormatException => println("you have to input numbers\n")
  }
}


def parseCSV2(csv: String, grid: Grid) = {
  Try {
    csv.split("\n").map { entry =>
      val token = entry.split(" ")
      val x = token(0).toInt
      val y = token(1).toInt
      grid.getValue(x, y) match {
        case 0 => grid.setField(x, y, 3)
          print(grid.getValue(x, y))
        case 1 =>
          grid.setField(x, y, 2)
          print(grid.getValue(x, y))
        case _ =>
          print(x + " + " + y)
      }
    }
  }.getOrElse {
    print(false)
  }
}

parseCSV(string)

parseCSV2(string, Grid(10))