package Battleship.controller

import Battleship.util.Observable

class Controller(var grid:Grid) extends Observable{
  def createEmptyGrid(size: Int):Unit = {
    grid = new Grid(size);
    notifyObservers
  }
}
