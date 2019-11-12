package Battleship.controller

import Battleship.model.Grid
import Battleship.util.Observable

class Controller(var grid:Grid) extends Observable{
  def createGrid(size: Int):Unit = {
    grid = new Grid(size);
    notifyObservers
  }

  def gridToString: String = grid.toString

}
