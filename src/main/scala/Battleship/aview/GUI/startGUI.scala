package Battleship.aview.GUI


import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

import Battleship.controller.Controller
import Battleship.model.Player
import javax.imageio.ImageIO
import javax.swing.JTextField

import scala.swing._
import scala.swing.event.ButtonClicked


class startGUI(controller: Controller) extends MainFrame {

  val dimWidth = 800
  val dimHeight = 600
  // val dimWidth = 1600
  // val dimHeight = 900
  // val dimWidth = 1920
  // val dimHeight = 1080

  title = "Battleship"
  background = Color.GRAY
  preferredSize = new Dimension(dimWidth, dimHeight) // maybe fullscreen setting / 1600 * 900 / 800 * 600

  val backgroundIMG: BufferedImage =
    ImageIO.read(new File("src/main/scala/Battleship/aview/GUI/BattleShipPicture.jpg"))

  val imageLabel: ImagePanel = new ImagePanel {
    imagePath(backgroundIMG)
    preferredSize = new Dimension(dimWidth, dimHeight)
  }

  val startButton: Panel = new FlowPanel {
    val ButtonStartGame = new Button("start game")

    ButtonStartGame.background = Color.BLACK
    ButtonStartGame.foreground = Color.WHITE

    contents += ButtonStartGame

    listenTo(ButtonStartGame)

    val buttons: List[Button] = List(ButtonStartGame)
    reactions += {
      case ButtonClicked(b) =>

        if (b == ButtonStartGame) {
          if (chooseStart() == Dialog.Result.Ok) {
            print(controller.player_01)
            val gui = new Gui(controller)
            gui.visible = true
            startGUI.this.visible = false
          }
        }
    }
  }

  def chooseStart(): Dialog.Result.Value = { // @TODO replace JTextField
    val sizeOfField = new JTextField
    val player_one = new JTextField
    val player_two = new JTextField
    val message = Array(" mapsize (ex: 10): ", sizeOfField, " ", " player_one:", player_one, " ", " player_two:", player_two)
    val res = Dialog.showConfirmation(contents.head,
      message,
      optionType = Dialog.Options.YesNo,
      title = title)
    if (res == Dialog.Result.Ok) {
      // controller.fieldsize = sizeOfField @TODO import somehow customization of field
      controller.player_01 = Player(player_one.getText())
      controller.player_02 = Player(player_two.getText())
    }
    res
  }

  menuBar = new MenuBar {
    contents += new Menu("Creators") {
      contents += new MenuItem(scala.swing.Action(controller.creator_01.toString) {
      })
      contents += new Separator()
      contents += new MenuItem(scala.swing.Action(controller.creator_02.toString) {
      })
    }
  }

  contents = new BorderPanel {
    iconImage = backgroundIMG
    add(imageLabel, BorderPanel.Position.Center)
    add(startButton, BorderPanel.Position.South)
  }
  centerOnScreen()

}
