package Battleship.aview.GUI

import Battleship.controller.{CellChanged, Controller}

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

class FieldPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {

  val FieldColor = new Color(224, 224, 255)
  val label = new Label {
    print(row + " | " + column)
    text = myField.toString
    font = new Font("Verdana", 1, 36)
  }
  val field = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(51, 51)
    background = FieldColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        label.text = myField.toString
        repaint
      }
      case MouseClicked(source, point, modifiers, clicks, triggersPopup) => {
        repaint
      }
    }
  }

  def redraw = {
    contents.clear()
    label.text = myField.toString()

  }

  def myField = controller.grid_player_01.getValue(row, column)

}
