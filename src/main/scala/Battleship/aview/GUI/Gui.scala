package Battleship.aview.GUI

import Battleship.controller.{CellChanged, Controller}

import scala.swing._
import scala.swing.event._

class CellClicked(val row: Int, val column: Int) extends Event

class Gui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "Battleship"
  val gridSize = controller.grid_player_01.size
  var fields = Array.ofDim[FieldPanel](gridSize, gridSize)

  def gridPanel = new GridPanel(gridSize, gridSize) {
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    for {
      row <- 0 until gridSize
      column <- 0 until gridSize
    } {
      val fieldPanel = new FieldPanel(row, column, controller)
      contents += fieldPanel.field
      fields(row)(column) = fieldPanel
      listenTo(fieldPanel)
    }
  }

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
  }

  visible = true
  //redraw

  reactions += {
    case event: CellChanged => redraw
  }

  def redraw = {
    for {
      row <- 0 until gridSize
      column <- 0 until gridSize
    } {
      fields(row)(column).redraw
    }
    repaint()
  }
}
