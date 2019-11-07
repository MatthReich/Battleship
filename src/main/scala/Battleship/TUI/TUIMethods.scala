package Battleship.TUI

import Battleship.model
import Battleship.model.{Player, PlayerField}
import Battleship.TUI.TUIInterface._

import util.control.Breaks._

object TUIMethods {

  def printWelcome (): Unit = {
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    printf("%s\n%s%s\n%s\ncreated by:\n\t: %s\n\t: %s\n%s\n\n\n",
      sSharp, sSpace, name, sSharp, creator_01.toString, creator_02.toString, sSharp)
  }

  def setPlayer(): Unit = {
    print(Console.GREEN + "Please insert playername for player_01\n")
    player_01 = model.Player(scala.io.StdIn.readLine().toString)
    print(Console.CYAN + "Please insert playername for player_02\n" + Console.RESET)
    player_02 = model.Player(scala.io.StdIn.readLine().toString)
  }

  def printGetPlayer(): Unit = {
    printf("Actual playerconfiguartion:\n" +
      "\tPlayer One: " + Console.GREEN + "%s\n" + Console.RESET +
      "\tPlayer Two: " + Console.CYAN + "%s\n\n" + Console.RESET,
      player_01, player_02)
  }

  //noinspection ScalaStyle
  def printSetField(): Unit = {
    printf("insert fieldsize\noptions:\n\t10 \t for [10 x 10] field\n\t15 \t for [15 x 15] field\n\t20 \t for [20 x 20] field\n")
    val tmp = scala.io.StdIn.readLine().toInt
    tmp match {
      case 10 => fieldSize = tmp
        // @TODO import settings for 15 / 20 field
      case 15 => printSetField()
      case 20 => printSetField()
    }
  }

  def askShips(playerField: PlayerField, player: Player): Unit = {
    // @TODO variable saves settings for ship placement
    printf("please insert locations for ships\noptions:\n\t2 space ship:\t1\n\t3 space ship:\t1\n\t4 space ship:\t1\n")
    var count = 0
    breakable {
      for (a <- 0 to 19) {
        val inputControl = scala.io.StdIn.readLine()
        if (inputControl == " ") break
        val input = inputControl.split(" ")
        count += 1
        playerField.replaceEntry(getRow(input(0)), input(1).toInt, true)
      }
    }
  }

  def printField(field: PlayerField, nr: Int): Unit = {
    val output = field.printField(nr)
    print(output + "\n")
  }

  //noinspection ScalaStyle
  def getRow(row: String): Int = row match {
    case "A0" => 0
    case "A1" => 1
    case "A2" => 2
    case "A3" => 3
    case "A4" => 4
    case "A5" => 5
    case "A6" => 6
    case "A7" => 7
    case "A8" => 8
    case "A9" => 9
  }
}