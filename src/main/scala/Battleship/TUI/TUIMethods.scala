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

  def addShips(grid: Grid, player: Player, shipSettingRules: Array[Int]): Unit = {
    val internRules = shipSettingRules
    val shipLength2 = 0
    val shipLength3 = 1
    val shipLength4 = 2
    val shipLength5 = 3

    var exitStatement = true
    while (exitStatement) {
      val input = io.StdIn.readLine().split(" ")
      if (input(0).equals("q") || input(0).equals("exit")) {
        exitStatement = false
      } else {
        if (input.length > 2 && input.length <= 10) {
          if (input.length % 2 == 0) {
            if (internRules(0) != 0 && internRules(1) != 0 && internRules(2) != 0 && internRules(3) != 0) {
              input.length match {
                case  4 => internRules(shipLength2) -= 1
                case  6 => internRules(shipLength3) -= 1
                case  8 => internRules(shipLength4) -= 1
                case 10 => internRules(shipLength5) -= 1
              }
              var idx = 0
              while (idx < input.length) {
                grid.setField(input(idx).toInt, input(idx + 1).toInt, 1)
                idx += 2
              }
            } else if (input.length == 4 && internRules(shipLength2) != 0) {
              internRules(shipLength2) -= 1
              var idx = 0
              while (idx < input.length) {
                grid.setField(input(idx).toInt, input(idx + 1).toInt, 1)
                idx += 2
              }
            } else if (input.length == 6 && internRules(shipLength3) != 0) {
              internRules(shipLength3) -= 1
              var idx = 0
              while (idx < input.length) {
                grid.setField(input(idx).toInt, input(idx + 1).toInt, 1)
                idx += 2
              }
            } else if (input.length == 8 && internRules(shipLength4) != 0) {
              internRules(shipLength4) -= 1
              var idx = 0
              while (idx < input.length) {
                grid.setField(input(idx).toInt, input(idx + 1).toInt, 1)
                idx += 2
              }
            } else if (input.length == 10 && internRules(shipLength5) != 0) {
              internRules(shipLength5) -= 1
              var idx = 0
              while (idx < input.length) {
                grid.setField(input(idx).toInt, input(idx + 1).toInt, 1)
                idx += 2
              }
            }

            print(printGrid(grid, player))
            print(printNrOfShips(internRules))

            if (internRules(0) == 0 && internRules(1) == 0 && internRules(2) == 0 && internRules(3) == 0) {
              exitStatement = false
            }
          }
        }
        println("try again")
      }
    }
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
