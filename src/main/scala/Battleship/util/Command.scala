package Battleship.util

trait Command {

  def setShip(): Unit

  def createShip(): Unit

  def undoCreate(): Unit

}
