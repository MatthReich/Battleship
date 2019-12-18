package Battleship.model.gridComponent

import Battleship.controller.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson

trait InterfaceGrid {

  def setField(x: Int, y: Int, value: Int): Unit

  def getField(x: Int, y: Int): Int

  def getValue(x: Int, y: Int): Int

  def toString(player: InterfacePerson, sortOfPrint: Boolean, playerStatus: PlayerState): String

}
