package Battleship.util

class UndoManager {
  private var undoStack: List[Command]= Nil
  def addShip(command: Command) = {
    undoStack = command::undoStack
    command.addShip
  }
}
