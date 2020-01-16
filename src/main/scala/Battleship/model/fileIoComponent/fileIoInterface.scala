package Battleship.model.fileIoComponent

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip

trait FileIOInterface {

  def load: (InterfaceGrid, InterfacePerson, Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState)

  def save(grid: InterfaceGrid): Unit

}