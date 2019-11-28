package Battleship.util

trait Command {

  def setShip: Unit

  def doStep: Unit

}
