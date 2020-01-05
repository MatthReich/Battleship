package Battleship.util

import Battleship.GameModule
import Battleship.controller.{InterfaceController, PlayerState, ProcessCommand, SetCommand}
import Battleship.model.gridComponent.InterfaceGrid
import com.google.inject.Guice
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {
  "A UndoManager" when {
    "new" should {
      val undoManager: UndoManager = new UndoManager
      val injector = Guice.createInjector(new GameModule)
      val grid1: InterfaceGrid = injector.getInstance(classOf[InterfaceGrid])
      val grid2: InterfaceGrid = injector.getInstance(classOf[InterfaceGrid])
      val shipCoordsSetting: Array[Int] = Array(0, 0, 0, 0)
      val controller: InterfaceController = injector.getInstance(classOf[InterfaceController])

      "setValue" in {
        //SetCommand
        undoManager.setValue(new SetCommand(PlayerState.PLAYER_ONE, shipCoordsSetting, controller))
        undoManager.setValue(new SetCommand(PlayerState.PLAYER_TWO, shipCoordsSetting, controller))
        //ProcessCommand
        undoManager.setValue(new ProcessCommand("1 1", grid1, PlayerState.PLAYER_ONE, controller))
        undoManager.setValue(new ProcessCommand("1 1", grid2, PlayerState.PLAYER_TWO, controller))
      }

      "undoStep" in {
        //SetCommand
        undoManager.undoStep(new SetCommand(PlayerState.PLAYER_ONE, shipCoordsSetting, controller))
        undoManager.undoStep(new SetCommand(PlayerState.PLAYER_TWO, shipCoordsSetting, controller))
        //ProcessCommand
        undoManager.undoStep(new ProcessCommand("1 1", grid1, PlayerState.PLAYER_ONE, controller))
        undoManager.undoStep(new ProcessCommand("1 1", grid2, PlayerState.PLAYER_TWO, controller))
      }
    }
  }
}
