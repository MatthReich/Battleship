package Battleship.TUI

import Battleship.model
import Battleship.model.{Grid, Player, PlayerField}
import Battleship.TUI.TUIInterface._

import scala.collection.immutable.ListMap
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

  def printSetField(): Int = {
    printf("insert field-size\noptions:\n\t10 \t for [10 x 10] field\n\t15 \t for [15 x 15] field\n\t20 \t for [20 x 20] field\n")
    val tmp = scala.io.StdIn.readLine().toInt
    tmp
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

  def printGrid(grid: Grid, player: Player): String ={
    val stringOfGrid = new mutable.StringBuilder("")
    stringOfGrid ++= ("Field of: " + Console.GREEN + player + Console.RESET + "\n")
    stringOfGrid ++= "     0    1    2    3    4    5    6    7    8    9\n"
    var idy = 0
    while (idy < grid.size){
      var idx = 0
      stringOfGrid ++= "A" + idy + " "
      while (idx < grid.size){
        val tmp = grid.getField(idx, idy)
        tmp match {
          case 0 => stringOfGrid ++= Console.BLUE + "  ~  " + Console.RESET
          case 1 => stringOfGrid ++= Console.GREEN + "  x  " + Console.RESET
          case 2 => stringOfGrid ++= Console.RED + "  x  " + Console.RESET
          case 3 => stringOfGrid ++= Console.BLUE + "  0  " + Console.RESET
        }
        idx += 1
      }
      idy += 1
      stringOfGrid ++= "\n"
    }
    stringOfGrid.toString()

  }
}
