package Battleship.util

class UndoManager {
  private var undoStack: List[Command] = Nil

  def setValue(command: Command): Unit = {
    undoStack = command :: undoStack
    command.setValue()
  }

  def undoStep(command: Command): Unit = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep()
        undoStack = stack
      }
    }
  }
}
