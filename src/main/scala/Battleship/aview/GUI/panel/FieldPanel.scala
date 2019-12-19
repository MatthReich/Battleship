package Battleship.aview.GUI.panel

import java.awt.Color

import Battleship.controller.{CellChanged, Controller, PlayerState}

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, FlowPanel, Label, Orientation, Swing}

class FieldPanel(you: Boolean, column: Int, row: Int, controller: Controller) extends FlowPanel {

  val field = new BoxPanel(Orientation.Vertical) {
    myField match {
      case 0 => background = Color.BLUE
        contents += new Label("~")
      case 1 => background = Color.GREEN
        contents += new Label("x")
      case 2 => background = Color.RED
        contents += new Label("x")
      case 3 => background = new Color(148, 197, 229)
        contents += new Label("0")
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

  def myField: Int = {
    if (controller.playerState == PlayerState.PLAYER_ONE) {
      if (you == false) controller.grid_player_01.getValue(column, row)
      else controller.grid_player02.getField(column, row)
    } else {
      if (you == false) controller.grid_player_02.getValue(column, row)
      else controller.grid_player01.getField(column, row)
    }
  }
}
