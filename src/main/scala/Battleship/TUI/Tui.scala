package Battleship.TUI

import Battleship.controller.Controller
import Battleship.util.Observer

class Tui(controller: Controller) extends Observer {

  var boolean: Boolean = true

  def provcessLine(input: String): Unit = {
    if (boolean) boolean = false
    input match {
      case "q" =>
      case "playerConfig" =>
      case _ =>
    }
  }


  override def update: Boolean = {
    if (boolean) {
      print(controller.gridToString(0, boolean))
      print(controller.gridToString(1, boolean))
    } else {
      print(controller.gridToString(0, boolean))
    }
    true
  }
}
