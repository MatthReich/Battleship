package Battleship.aview.GUI

import Battleship.controller.Controller
import Battleship.model.gridComponent.advancedGrid.Grid

object TestForGUI {
  def main(args: Array[String]): Unit = {
    val startGUI = new startGUI(new Controller(Grid(10), Grid(10)))
    startGUI.visible = true
  }
}
