package Battleship.controller

import Battleship.util.Observable

class Controller(var grid:Grid) extends Observable{
  def createEmptyGrid(size: Int):Unit = {
    grid = new Grid(size);
    notifyObservers
  }

  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: Int):Unit = {
    grid = grid.set(row, col, value)
    notifyObservers
  }
}
