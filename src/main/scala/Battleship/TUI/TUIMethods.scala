package Battleship.TUI

import Battleship.model
import Battleship.model.{Player, PlayerField}
import Battleship.TUI.TUIInterface._

import scala.collection.mutable
import util.control.Breaks._

object TUIMethods {

  def printWelcome (): String = {
    val stringPrint = new mutable.StringBuilder("")
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    stringPrint ++= (sSharp + "\n" + sSpace + name + "\n" + sSharp + "\ncreated by:\n\t: " + creator_01.toString +
      "\n\t: " + creator_02.toString + "\n" + sSharp + "\n\n\n")
    stringPrint.toString()
  }

  def setPlayer(): Unit = {
    print(Console.GREEN + "Please insert player name for player_01\n")
    player_01 = model.Player(scala.io.StdIn.readLine().toString)
    if(player_01.name == "") {
      player_01 = model.Player("player_01")
    }
    print(Console.CYAN + "Please insert player name for player_02\n" + Console.RESET)
    player_02 = model.Player(scala.io.StdIn.readLine().toString)
    if(player_02.name == "") {
      player_02 = model.Player("player_02")
    }
  }

  def printGetPlayer(): String = {
    val stringPrint = new mutable.StringBuilder("")
    stringPrint ++= ("Actual player-configuration\n" +
      "\tPlayer One: " + Console.GREEN + player_01 + "\n" + Console.RESET +
      "\tPlayer Two: " + Console.CYAN + player_02 + "\n\n" + Console.RESET)
    stringPrint.toString()
  }

  //noinspection ScalaStyle
  @scala.annotation.tailrec
  def printSetField(): Unit = {
    printf("insert field-size\noptions:\n\t10 \t for [10 x 10] field\n\t15 \t for [15 x 15] field\n\t20 \t for [20 x 20] field\n")
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
    val shipPlacements = 19
    breakable {
      for (_ <- 0 to shipPlacements) {
        val inputControl = scala.io.StdIn.readLine()
        if (inputControl == " " || inputControl == "" || inputControl == "q") break
        val input = inputControl.split(" ")
        count += 1
        playerField.replaceEntry(getRow(input(0)), input(1).toInt, boolean = true)
      }
    }
  }

  def printField(field: PlayerField, nr: Int): String = {
    val printString = new mutable.StringBuilder("")
    val output = field.printField(nr)
    printString ++= (output + "\n")
    printString.toString()
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
