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
    val name = scala.io.StdIn.readLine().toString
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
      Console.GREEN + "Please insert player name for player_01\n" + Console.RESET
    } else {
      Console.CYAN + "Please insert player name for player_02\n" + Console.RESET
    }
  }

}
