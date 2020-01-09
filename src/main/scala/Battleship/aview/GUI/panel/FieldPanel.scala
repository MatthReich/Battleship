package Battleship.aview.GUI.panel

import java.awt.Color

import Battleship.controller.{CellChanged, InterfaceController, PlayerState}

import scala.swing.event.UIEvent
import scala.swing.{BoxPanel, FlowPanel, Orientation, Swing}

class FieldPanel(you: Boolean, column: Int, row: Int, controller: InterfaceController) extends FlowPanel {

  val field = new BoxPanel(Orientation.Vertical) {
    myField match {
      case 0 => background = Color.BLUE
        contents += new CoordLabel(column, row, "~")
      case 1 => background = Color.GREEN
        contents += new CoordLabel(column, row, "x")
      case 2 => background = Color.RED
        contents += new CoordLabel(column, row, "x")
      case 3 => background = new Color(148, 197, 229)
        contents += new CoordLabel(column, row, "0")
    }
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        repaint
      }
      case event: UIEvent => {
        print(event.source + "\n")
        event.source.background = Color.GRAY
        val x = event.source.asInstanceOf[CoordLabel].getX()
        val y = event.source.asInstanceOf[CoordLabel].getY()
        val string: String = x + y + ""
        var grid = controller.getGridPlayer1
        controller.getPlayerState match {
          case PlayerState.PLAYER_TWO => grid = controller.getGridPlayer2
        }
        controller.checkGuess(string, grid)
        repaint
      }
    }
  }

  def myField: Int = {
    if (controller.getPlayerState == PlayerState.PLAYER_ONE) {
      if (you == false) controller.getGridPlayer1.getValue(column, row)
      else controller.getGridPlayer2.getField(column, row)
    } else {
      if (you == false) controller.getGridPlayer2.getValue(column, row)
      else controller.getGridPlayer1.getField(column, row)
    }
  }
}
