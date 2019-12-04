package Battleship.aview

import Battleship.controller.Controller
import Battleship.model.{Creator, Player}
import Battleship.util.Observer

import scala.collection.mutable


class TUIInterface(controller: Controller) extends Observer {

  controller.add(this)

  def printWelcomeX(): Unit = {
    print(printWelcome(controller.creator_01, controller.creator_02))
  }

  def setPlayers(input: String, color: Int): Unit = {
    var player: Player = Player(" ")
    if (input != "") {
      player = Player(input)
    } else {
      player = Player("player_0" + color)
    }
    if (color == 1) {
      controller.player_01 = player
    } else {
      controller.player_02 = player
    }
  }

  def playerConfiguration(): Unit = {
    print(printGetPlayer(controller.player_01, controller.player_02))
  }

  def printWelcome(creator_01: Creator, creator_02: Creator): String = {
    val stringPrint = new mutable.StringBuilder("")
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    stringPrint ++= (sSharp + "\n" + sSpace + name + "\n" + sSharp + "\ncreated by:\n\t: " + creator_01.toString +
      "\n\t: " + creator_02.toString + "\n" + sSharp + "\n\n\n")
    stringPrint.toString()
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

  override def update: Boolean = {
    true
  }
}
