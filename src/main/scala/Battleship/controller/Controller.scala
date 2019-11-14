package Battleship.controller

import Battleship.TUI.{TUIInterface, TUIMethods}
import Battleship.model.{Creator, Grid, Player}
import Battleship.util.Observable

class Controller() extends Observable {

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  val fieldSize: Int = 10
  val playerGrid_01: Grid = Grid(fieldSize)
  val playerGrid_02: Grid = Grid(fieldSize)
  var player_01 = Player("")
  var player_02 = Player("")
  var nr = Array[Int](4, 3, 2, 1)

  def setPlayer(): Unit = {
    player_01 = TUIMethods.setPlayer(1)
    player_02 = TUIMethods.setPlayer(2)
    notifyObservers()
  }

  def printPlayer(): Unit = {
    TUIInterface.printGetPlayer(player_01, player_02)
    notifyObservers()
  }

  def printWelcome(): Unit = {
    TUIInterface.printWelcome(creator_01, creator_02)
    notifyObservers()
  }

  def printGrid(): Unit = {
    TUIInterface.printGrid(playerGrid_01, player_01)
    TUIInterface.printGrid(playerGrid_02, player_02)
    notifyObservers()
  }

  def addShips(): Unit = {
    while ((nr(0) + nr(1) + nr(2) + nr(3)) != 0) {
      val tmp = TUIMethods.addShips(playerGrid_01, player_01, nr)
      if (tmp(0) != 10) {
        playerGrid_01.setShip(tmp, nr)
      }
    }
    nr = Array[Int](4, 3, 2, 1)
    while ((nr(0) + nr(1) + nr(2) + nr(3)) != 0) {
      val tmp = TUIMethods.addShips(playerGrid_02, player_02, nr)
      if (tmp(0) != 10) {
        playerGrid_01.setShip(tmp, nr)
      }
    }
    notifyObservers()
  }
}
