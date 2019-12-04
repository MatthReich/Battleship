package Battleship.util

class UndoManager {
  private var undoStack: List[Command]= Nil
  def setShip(command: Command): Unit = {
    undoStack = command :: undoStack
    command.setShip()
  }
  def createShip(command: Command): Unit = {
    undoStack = command :: undoStack
    command.createShip()
  }
}
