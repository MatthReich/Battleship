package Battleship.model.fileIoComponent

import Battleship.model.gridComponent.InterfaceGrid

trait FileIOInterface {

  def load: InterfaceGrid

  def save(grid: InterfaceGrid): Unit

}