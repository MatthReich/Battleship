package Battleship.aview.GUI

import Battleship.controller.{CellChanged, Controller}

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, FlowPanel, Label, Orientation, Swing}

class FieldPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {

  val field = new BoxPanel(Orientation.Vertical) {
    contents += new Label(myField.toString)
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        repaint
      }
      case MouseClicked(source, point, modifiers, clicks, popup) => {
        repaint
      }
    }
  }

  def redraw = {
    contents.clear()
    contents += new Label(myField.toString)
    repaint
  }

  def myField: Int = controller.grid_player_01.getValue(row, column)
}
