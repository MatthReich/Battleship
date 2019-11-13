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


}
