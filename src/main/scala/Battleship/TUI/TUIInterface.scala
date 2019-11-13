package Battleship.TUI

import Battleship.model.{Creator, Grid, Player}
import Battleship.TUI.TUIMethods._

object TUIInterface {


  def printGrid(grid: Grid, player: Player): Unit ={
    print(TUIMethods.printGrid(grid, player))
  }
  def printWelcome (creator_01: Creator, creator_02: Creator): Unit ={
    print(TUIMethods.printWelcome(creator_01, creator_02))
  }
  def printGetPlayer(player_01: Player,player_02: Player): Unit ={
    print(TUIMethods.printGetPlayer(player_01, player_02))
  }

  def execute(): Unit = {
    //askShips(playerField_01, player_01)
    //askShips(playerField_02, player_02)

    //var fieldP_01 = PlayerField(player_01, fieldSize)
    //var fieldP_02 = PlayerField(player_02, fieldSize)

    // @TODO add color to Player itself
    //print(printGrid(playerField_01, player_01))
    //print(printGrid(playerField_02, player_02))
    // printField(fieldP_01, 1)
    // printField(fieldP_02, 2)


    /*var turn = true
    while (true) {
      if (turn) print(player_01 + " is on turn\n")
      if (!turn) print(player_02.name + " is on turn\n")

      val input = scala.io.StdIn.readLine().toString.split(" ")

      if (input.length == 1) {
        input(0) match {
          case "getPlayerStats" => print(printGetPlayer())
          case "q" => return
          case "exit" => return
          case _ => AnyRef
        }

        if (turn) turn = false
        else turn = true

      } else {
        val row = getRow(input(0))
        val column = input(1).toInt

        if (turn) { // player one guessing
          val guess = fieldP_02_Ships.getEntry(row, column)
          if (guess) {
            fieldP_02.replaceEntry(row, column, true)
            print(printGrid(playerField_02, player_02))
          }
          if (!turn) turn = true
          else turn = false

        } else {
          val guess = fieldP_01_Ships.getEntry(row, column)
          if (guess) {
            fieldP_01.replaceEntry(row, column, true)
            print(printGrid(playerField_01, player_01))
          }
          if (!turn) turn = true
          else turn = false
        }
      }
    }*/

  }
}

// when ship is sunk -> turn colour
// player sets first location then have to select one we created
