package Battleship.aview.TUI

import Battleship.controller.InterfaceController
import Battleship.model.Person.InterfacePerson

import scala.collection.mutable
import scala.swing.Reactor


class TUIInterface(controller: InterfaceController) extends Reactor {

  listenTo(controller)

  def printWelcomeX(): Unit = {
    print(printWelcome(controller.getCreator1, controller.getCreator2))
  }

  def playerConfiguration(): Unit = {
    print(printGetPlayer(controller.getPlayer1, controller.getPlayer2))
  }

  def printWelcome(creator_01: InterfacePerson, creator_02: InterfacePerson): String = {
    val stringPrint = new mutable.StringBuilder("")
    val sSharp: String = "#" * 30
    val sSpace: String = " " * 10
    val name: String = "Battleship"
    stringPrint ++= (sSharp + "\n" + sSpace + name + "\n" + sSharp + "\ncreated by:\n\t: " + creator_01.toString +
      "\n\t: " + creator_02.toString + "\n" + sSharp + "\n\n\n")
    stringPrint.toString()
  }

  def printGetPlayer(player_01: InterfacePerson, player_02: InterfacePerson): String = {
    val stringPrint = new mutable.StringBuilder("")
    stringPrint ++= ("Actual player-configuration\n" +
      "\tPlayer One: " + Console.GREEN + player_01.toString + "\n" + Console.RESET +
      "\tPlayer Two: " + Console.CYAN + player_02.toString + "\n\n" + Console.RESET)
    stringPrint.toString()
  }

  def printSetPlayer(color: Int): String = {
    if (color == 1) {
      Console.GREEN + "Please insert player name for player_01\n" + Console.RESET
    } else {
      Console.CYAN + "Please insert player name for player_02\n" + Console.RESET
    }
  }

  def update: Boolean = {
    true
  }
}
