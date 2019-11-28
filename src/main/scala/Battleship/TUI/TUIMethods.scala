package Battleship.TUI

import Battleship.model.{Creator, Player}

import scala.collection.mutable

object TUIMethods {

  def printWelcome(creator_01: Creator, creator_02: Creator): String = {
    val stringPrint = new mutable.StringBuilder("")
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    stringPrint ++= (sSharp + "\n" + sSpace + name + "\n" + sSharp + "\ncreated by:\n\t: " + creator_01.toString +
      "\n\t: " + creator_02.toString + "\n" + sSharp + "\n\n\n")
    stringPrint.toString()
  }

  def setPlayer(color: Int): Player = {
    val name = input()
    if (name != "") {
      val player: Player = Player(name)
      player
    } else {
      val player: Player = Player("player_0" + color)
      player
    }
  }

  def printGetPlayer(player_01: Player, player_02: Player): String = {
    val stringPrint = new mutable.StringBuilder("")
    stringPrint ++= ("Actual player-configuration\n" +
      "\tPlayer One: " + Console.GREEN + player_01.name + "\n" + Console.RESET +
      "\tPlayer Two: " + Console.CYAN + player_02.name + "\n\n" + Console.RESET)
    stringPrint.toString()
  }

  def printSetPlayer(color: Int): String = {
    if (color == 1) {
      (Console.GREEN + "Please insert player name for player_01\n" + Console.RESET)
    } else {
      (Console.CYAN + "Please insert player name for player_02\n" + Console.RESET)
    }
  }

  def printNrOfShips(nr: Array[Int]): String = {
    val string = new mutable.StringBuilder("")
    string ++= "Please set your Ships:\n"
    var idx = 2
    for (tmp <- nr) {
      string ++= ("You can still place: " + Console.GREEN + tmp + "x " + Console.RESET + " " + idx + " Block Ship\n")
      idx += 1
    }
    string.toString()
  }

  def checkValidShip(input: String): Array[Int] = {
    val inputInner = input.split(" ")
    val tmp: Array[Int] = new Array[Int](4)
    try {
      if (inputInner.length == 4) {   // schöner mit for <- schleife lösen
        var idx = 0
        while (idx < 4) {
          tmp(idx) = inputInner(idx).toInt
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

  def input(): String = {
    scala.io.StdIn.readLine().toString
  }

  def output(output: String): Unit = {
    print(output)
  }

}
