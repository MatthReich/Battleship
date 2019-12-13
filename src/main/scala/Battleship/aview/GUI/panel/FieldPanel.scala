package Battleship.aview.GUI.panel

import Battleship.controller.{CellChanged, Controller}

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, FlowPanel, Label, Orientation, Swing}

class FieldPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {

  val field = new BoxPanel(Orientation.Vertical) {
    myField match {
      case 0 => contents += new Label("~")
      case 1 => contents += new Label("x")
      case 2 => contents += new Label("x")
      case 3 => contents += new Label("0")
    }
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
