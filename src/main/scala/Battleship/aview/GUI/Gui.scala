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

  def gridPanel = new GridPanel(gridSize + 1, gridSize) {
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    for {
      row <- 0 until gridSize + 1
    } {
      if (row == 0) {
        contents += new Label("")
      } else {
        contents += new Label("" + row)
      }
    }
    for {
      row <- 0 until gridSize
      column <- 0 until gridSize
    } {
      if (column == 0) {
        contents += new Label("A" + (row + 1))

      }
      val fieldPanel = new FieldPanel(row, column, controller)
      contents += fieldPanel.field
      fields(row)(column) = fieldPanel
      listenTo(fieldPanel)
    }
  }

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("New") {
        /*@TODO Action New*/
      })
      contents += new MenuItem(Action("Quit") {
        closeMe()
      })
    }
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo") {
        /*@TODO Action Undo*/
      })
    }
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

  def closeMe() {
    val res = Dialog.showConfirmation(contents.head,
      "Do you really want to quit?",
      optionType = Dialog.Options.YesNo,
      title = title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  }
}
