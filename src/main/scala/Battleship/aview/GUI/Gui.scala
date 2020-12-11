package Battleship.aview.GUI

import java.awt.image.BufferedImage
import java.io.File

import Battleship.aview.GUI.panel.FieldPanel
import Battleship.controller.ControllerBaseImpl.{CellChanged, ExitGame, GameState, PlayerState}
import Battleship.controller._
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import javax.imageio.ImageIO

import scala.swing._

class Gui(controller: InterfaceController) extends Frame {

  listenTo(controller)

  var you: Boolean = true
  var shipCoords: Int = 0
  var ship: String = ""
  var last: String = ""
  title = "Battleship"
  preferredSize = new Dimension(800, 600)
  val gridSize = controller.getGridPlayer1.getSize

  def redraw = {
    contents = new BorderPanel {
      add(playGrid, BorderPanel.Position.Center)
      add(textGrid, BorderPanel.Position.North)
    }
  }

  def writeShip(string: String): Unit = {
    if (string == last) {

    } else {
      shipCoords += 1
      ship += string
      last = string
      if (shipCoords == 1) ship += " "
      else {
        controller.setShip(ship)
        ship = ""
        shipCoords = 0
        if (controller.getGameState == GameState.SHIPSETTING) {
          if ((controller.getNrPlayer1()(0) + controller.getNrPlayer1()(1) + controller.getNrPlayer1()(2) +
            controller.getNrPlayer1()(3)) == 0) {
            controller.setPlayerState(PlayerState.PLAYER_TWO)
          }
          if (controller.getNrPlayer2()(0) + controller.getNrPlayer2()(1) + controller.getNrPlayer2()(2) +
            controller.getNrPlayer2()(3) == 0) {
            controller.setPlayerState(PlayerState.PLAYER_ONE)
            controller.setGameState(GameState.IDLE)
          }
        }
        redraw
      }
    }
  }


  def playGrid = new GridPanel(1, 2) {
    you = true
    contents += gridPanel
    you = false
    contents += gridPanel
  }

  def decreaseShipNumbersToPlace(ship: InterfaceShip, boolean: Boolean, increase: Boolean): Unit = {
    if (boolean || increase) {
      val shipSize: Int = ship.getSize
      var x: Int = -1
      if (increase) {
        x = 1
      }
      shipSize match {
        case 2 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(0, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(0, x)
          }
        case 3 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(1, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(1, x)
          }
        case 4 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(2, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(2, x)
          }
        case 5 =>
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => controller.setNrPlayer1(3, x)
            case PlayerState.PLAYER_TWO => controller.setNrPlayer2(3, x)
          }
      }
    }
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("save") {
        controller.save()
      })
      contents += new MenuItem(Action("load") {
        controller.load()
      })
      contents += new MenuItem(Action("quit") {
        closeMe()
      })
    }
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo") {
        val grid: InterfaceGrid = controller.getPlayerState match {
          case PlayerState.PLAYER_ONE => controller.getGridPlayer2
          case PlayerState.PLAYER_TWO => controller.getGridPlayer1
        }
        controller.undoGuess(controller.getLastGuess(), grid)
      })
    }
  }

  val backgroundIMG: BufferedImage =
    ImageIO.read(new File("src/main/scala/Battleship/aview/GUI/media/BattleShipPicture.jpg"))

  iconImage = backgroundIMG
  visible = true
  redraw

  reactions += {
    case changed: CellChanged => redraw
    case game: ExitGame => sys.exit(0)
  }

  def textGrid = new GridPanel(1, 2) {
    var nameEnemy: String = ""
    var nameYou: String = ""
    controller.getPlayerState match {
      case PlayerState.PLAYER_ONE =>
        nameEnemy = controller.getPlayer2.toString
        nameYou = controller.getPlayer1.toString
      case PlayerState.PLAYER_TWO =>
        nameEnemy = controller.getPlayer1.toString
        nameYou = controller.getPlayer2.toString
    }
    contents += new TextArea(nameEnemy)
    contents += new TextArea("On turn: " + nameYou)
  }

  def closeMe() {
    val res = Dialog.showConfirmation(contents.head,
      "Do you really want to quit?",
      optionType = Dialog.Options.YesNo,
      title = title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
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
      val fieldPanel = new FieldPanel(you, column, row, controller, Gui.this)
      contents += fieldPanel.field
      listenTo(fieldPanel)
    }
  }

  centerOnScreen()
}
