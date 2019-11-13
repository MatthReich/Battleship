package Battleship

import Battleship.controller.Controller

object Game {
  def main(args: Array[String]): Unit = {
    val controller:Controller = new Controller()
    controller.printWelcome()
    controller.setPlayer()
    controller.printPlayer()
    controller.printGrid()
    controller.addShips()
    controller.printGrid()

  }
}
