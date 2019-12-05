package Battleship.util

class UndoManager {
  private var undoStack: List[Command] = Nil

  def setShip(command: Command): Unit = {
    undoStack = command :: undoStack
    command.setShip()
  }

  def createShip(command: Command): Unit = {
    undoStack = command :: undoStack
    command.createShip()
  }

  def undoCreate(command: Command): Unit = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoCreate()
        undoStack = stack
      }
    }
  }
}
