package Battleship.TUI

import Battleship.model.Grid

object TUIProcessLine {

  def processLineIntern(playerStatus: Boolean, playerInput: String, grid: Grid): Boolean = {
    var hit = false
    val input = playerInput.split(" ")

    try {
      if (input.length == 2) {   // schöner mit for <- schleife lösen
        val x = input(0).toInt
        val y = input(1).toInt

        grid.getValue(x, y) match {
          case 1 => {
            hit = true
            grid.setField(x, y, 2)
          }
          case 2 => grid.setField(x, y, 3)
          case _ =>
        }
      } else {
        print("Format Error\n")
      }
    } catch {
      case e: NumberFormatException => println("you have to input numbers\n", e)
    }

    if (hit) {
      playerStatus
    } else {
      !playerStatus
    }
  }

}
