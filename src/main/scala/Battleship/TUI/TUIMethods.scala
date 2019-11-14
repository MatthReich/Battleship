package Battleship.TUI

import Battleship.model.{Creator, Grid, Player}

import scala.collection.mutable

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

  def addShips(grid: Grid, player: Player): Array[Int] = {
    printGrid(grid, player)
    val input = io.StdIn.readLine().split(" ")
    val tmp: Array[Int] = new Array[Int](4)
    try {
      if (input.length == 4) {
        var idx = 0
        while (idx < 4) {
          tmp(idx) = input(idx).toInt
          idx += 1
        }

      } else {
        print("Format Error\n")
        tmp(0) = 10
      }
    } catch {
      case e: NumberFormatException => println("you have to input numbers\n", e)
        tmp(0) = 10
    }
    if ((tmp(0) >= 0 && tmp(0) <= 9) && (tmp(1) >= 0 && tmp(1) <= 9) && (tmp(2) >= 0 && tmp(2) <= 9) && (tmp(3) >= 0 && tmp(3) <= 9)) {

    } else {
      tmp(0) = 10
    }
    tmp
  }


  def printNrOfShips(nr:Array[Int]): String = {
    val string = new mutable.StringBuilder("")
    string ++= ("Please set your Ships:\n")
    string ++= ("You can still place: " + Console.GREEN + nr(0) + Console.RESET + "x 2 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(1) + Console.RESET + "x 3 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(2) + Console.RESET + "x 4 Block Ship\n")
    string ++= ("You can still place: " + Console.GREEN + nr(3) + Console.RESET + "x 5 Block Ship\n")
    string.toString()
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
