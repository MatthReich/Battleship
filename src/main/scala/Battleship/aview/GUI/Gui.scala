package Battleship.aview.GUI

import Battleship.aview.GUI.panel.FieldPanel
import Battleship.controller.{CellChanged, InterfaceController}

import scala.swing._

class Gui(controller: InterfaceController) extends Frame {

  listenTo(controller)

  var you: Boolean = true
  title = "Battleship"
  preferredSize = new Dimension(800, 600)
  val gridSize = controller.getGridPlayer1.getSize

  def redraw = {
    contents = new BorderPanel {
      add(playGrid, BorderPanel.Position.Center)
      add(textGrid, BorderPanel.Position.North)
    }
  }

  def playGrid = new GridPanel(1, 2) {
    you = true
    contents += gridPanel
    you = false
    contents += gridPanel
  }

  def gridPanel = new GridPanel(gridSize + 1, gridSize) {
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    for {
      row <- 0 until gridSize
    } {
      if (row == 0) {
        contents += new Label("")
      }
        contents += new Label("" + row)
    }
    for {
      row <- 0 until gridSize
      column <- 0 until gridSize
    } {
      if (column == 0) {
        contents += new Label("A" + (row))

      }
      val fieldPanel = new FieldPanel(you, column, row, controller)
      contents += fieldPanel.field
      listenTo(fieldPanel)
    }
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
  redraw

  reactions += {
    case event: CellChanged => redraw
  }

  def textGrid = new GridPanel(1, 2) {
    contents += new TextArea("You")
    contents += new TextArea("Enemy")
  }

  def closeMe() {
    val res = Dialog.showConfirmation(contents.head,
      "Do you really want to quit?",
      optionType = Dialog.Options.YesNo,
      title = title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  }

  centerOnScreen()

}
