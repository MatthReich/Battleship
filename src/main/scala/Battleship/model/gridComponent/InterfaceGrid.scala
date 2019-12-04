package Battleship.model.gridComponent

import Battleship.controller.PlayerStatus.PlayerStatus
import Battleship.model.Player

trait InterfaceGrid {

  def setField(x: Int, y: Int, value: Int): Unit

  def getField(x: Int, y: Int): Int

  def getValue(x: Int, y: Int): Int

  def toString(player: Player, sortOfPrint: Boolean, playerStatus: PlayerStatus): String

}
