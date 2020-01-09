package Battleship.aview.GUI.panel

import scala.swing.Label

class CoordLabel(x: Int, y: Int, data: String) extends Label {
  text = data

  def getX(): Int = x

  def getY(): Int = y
}
