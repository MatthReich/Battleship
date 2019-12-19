package Battleship.aview

import Battleship.aview.GUI.panel.ImagePanel
import Battleship.aview.GUI.startGUI
import Battleship.controller.{Controller, InterfaceController}
import Battleship.model.gridComponent.advancedGrid.Grid
import org.scalatest.{Matchers, WordSpec}

class GuiSpec() extends WordSpec with Matchers {

  "A Gui" when {
    "new" should {
      var controller: InterfaceController = new Controller(Grid(10), Grid(10))
      var imagePanel: ImagePanel = new ImagePanel
      var startGui: startGUI = new startGUI(controller)
      "imagePath" in {
        imagePanel.imagePath(startGui.backgroundIMG)
      }

      "chooseStart" in {
        startGui.chooseStart()
      }

    }
  }
}
