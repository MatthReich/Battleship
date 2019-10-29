package Battleship.TUI

import Battleship.model
import Battleship.model.PlayerField
import Battleship.TUI.TUIInterface._

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
      case 15 => printSetField()
      case 20 => printSetField()
    }
  }

  def printField(field: PlayerField, nr: Int): Unit = {
    val output = field.printField(nr)
    print(output + "\n")
  }

}
