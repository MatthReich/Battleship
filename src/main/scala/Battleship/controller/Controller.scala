package Battleship.controller

import Battleship.TUI.{TUIInterface, TUIMethods}
import Battleship.model.{Creator, Grid, Player}
import Battleship.util.Observable

class Controller() extends Observable{

  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01 = Player("")
  var player_02 = Player("")
  var fieldSize: Int = 10
  var playerGrid_01: Grid = new Grid(fieldSize)
  var playerGrid_02: Grid = new Grid(fieldSize)

  def setPlayer():Unit ={
    player_01 = TUIMethods.setPlayer(1)
    player_02 = TUIMethods.setPlayer(2)
    notifyObservers
  }

  def printPlayer():Unit ={
    TUIInterface.printGetPlayer(player_01, player_02)
    notifyObservers
  }

  def printWelcome():Unit ={
    TUIInterface.printWelcome(creator_01, creator_02)
    notifyObservers
  }


}
