package Battleship.util

trait Command {

  def setValue(): Unit

  def undoStep(): Unit

}
