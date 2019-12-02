package Battleship.util

class UndoManager {
  private var undoStack: List[Command]= Nil
  def addShip(command: Command): Unit = {
    undoStack = command :: undoStack
    command.addShip()
  }
}
