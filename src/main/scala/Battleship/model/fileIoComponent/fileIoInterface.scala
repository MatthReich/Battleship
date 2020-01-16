package Battleship.model.fileIoComponent

import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid

trait FileIOInterface {

  def load: (InterfaceGrid, InterfacePerson)

  def save(grid: InterfaceGrid): Unit

}