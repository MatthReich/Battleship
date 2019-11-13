package Battleship.TUI

import Battleship.model.{Creator, Grid, Player}
import scala.collection.mutable
import util.control.Breaks._

object TUIMethods {

  def printWelcome (creator_01: Creator, creator_02: Creator): String = {
    val stringPrint = new mutable.StringBuilder("")
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    stringPrint ++= (sSharp + "\n" + sSpace + name + "\n" + sSharp + "\ncreated by:\n\t: " + creator_01.toString +
      "\n\t: " + creator_02.toString + "\n" + sSharp + "\n\n\n")
    stringPrint.toString()
  }

  def setPlayer(color:Int): Player = {
    if (color == 1) {
      print(Console.GREEN + "Please insert player name for player_01\n")
    } else {
      print(Console.CYAN + "Please insert player name for player_02\n" + Console.RESET)
    }
    val name = scala.io.StdIn.readLine().toString
    if (name != "") {
      val player: Player = Player(name)
      player
    }else {
      val player: Player = Player("player_0" + color)
      player
    }

  }

  def printGetPlayer(player_01: Player,player_02: Player): String = {
    val stringPrint = new mutable.StringBuilder("")
    stringPrint ++= ("Actual player-configuration\n" +
      "\tPlayer One: " + Console.GREEN + player_01.name + "\n" + Console.RESET +
      "\tPlayer Two: " + Console.CYAN + player_02.name + "\n\n" + Console.RESET)
    stringPrint.toString()
  }

  def askShips(grid: Grid, player: Player): Unit = {
    // @TODO variable saves settings for ship placement
    printf("please insert locations for ships\noptions:\n\t2 space ship:\t1\n\t3 space ship:\t1\n\t4 space ship:\t1\n")
    var count = 0
    val shipPlacements = 19
    breakable {
      for (_ <- 0 to shipPlacements) {
        val inputControl = scala.io.StdIn.readLine()
        if (inputControl == " " || inputControl == "" || inputControl == "q") break
        count += 1
        //playerField.replaceEntry(getRow(input(0)), input(1).toInt, boolean = true)
      }
    }
  }

  def printGrid(grid: Grid, player: Player): String ={
    val stringOfGrid = new mutable.StringBuilder("")
    stringOfGrid ++= ("Field of: " + Console.GREEN + player.name + Console.RESET + "\n")
    stringOfGrid ++= "   "
    var ids = 0
    while (ids < grid.size) {
      stringOfGrid ++= "  " + ids + "  "
      ids += 1
    }
    stringOfGrid ++= "\n"
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
