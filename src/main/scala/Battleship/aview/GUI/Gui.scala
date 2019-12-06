package Battleship.aview.GUI

import Battleship.controller.Controller

import scala.swing._
import scala.swing.event.Event

class CellClicked(val row: Int, val column: Int) extends Event

class Gui(controller: Controller) extends Frame {

  title = "Battleship"
  var field = Array.ofDim[FieldPanel](controller.grid_player01.size, controller.grid_player01.size)

}
