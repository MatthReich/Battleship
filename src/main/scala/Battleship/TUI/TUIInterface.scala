package Battleship.TUI

import Battleship.model.{Creator, Player, PlayerField}
import Battleship.TUI.TUIMethods._

object TUIInterface {
  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01 = Player("")
  var player_02 = Player("")
  var fieldSize: Int = 0

  //noinspection ScalaStyle
  def execute(): Unit = {
    print(printWelcome())
    setPlayer()
    print(printGetPlayer())
    printSetField()

    val fieldP_01_Ships = PlayerField(player_01, fieldSize)
    val fieldP_02_Ships = PlayerField(player_02, fieldSize)

    print(printField(fieldP_01_Ships, 1))
    askShips(fieldP_01_Ships, player_01)
    print(printField(fieldP_02_Ships, 2))
    askShips(fieldP_02_Ships, player_02)

    var fieldP_01 = PlayerField(player_01, fieldSize)
    var fieldP_02 = PlayerField(player_02, fieldSize)

    // @TODO add color to Player itself
    print(printField(fieldP_01_Ships, 1))
    print(printField(fieldP_02_Ships, 2))
    // printField(fieldP_01, 1)
    // printField(fieldP_02, 2)


    var turn = true
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
            print(printField(fieldP_02, 2))
          }
          if (!turn) turn = true
          else turn = false

        } else {
          val guess = fieldP_01_Ships.getEntry(row, column)
          if (guess) {
            fieldP_01.replaceEntry(row, column, true)
            print(printField(fieldP_01, 1))
          }
          if (!turn) turn = true
          else turn = false
        }
      }
    }

  }
}

// when ship is sunk -> turn colour
// player sets first location then have to select one we created
