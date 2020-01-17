package Battleship.aview.GUI.panel

import java.awt.Color

import Battleship.controller.{CellChanged, GameState, InterfaceController, PlayerState}

import scala.swing.event.{UIEvent}
import scala.swing.{BoxPanel, FlowPanel, Label, Orientation, Swing}

class FieldPanel(you: Boolean, column: Int, row: Int, controller: InterfaceController) extends FlowPanel {

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
      case event: UIEvent => {
        event.source.background = Color.GRAY
        val x = column
        val y = row
        val string: String = x + " " + y
        controller.getGameState match {
          case GameState.SHIPSETTING =>
          case GameState.IDLE => controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.checkGuess(string, controller.getGridPlayer2)
            case PlayerState.PLAYER_TWO => controller.checkGuess(string, controller.getGridPlayer1)
          }
          case GameState.SOLVED => sys.exit(0)
        }
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
