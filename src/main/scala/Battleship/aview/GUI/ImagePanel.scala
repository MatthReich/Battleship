package Battleship.aview.GUI

import java.awt.image.BufferedImage
import scala.swing._

class ImagePanel extends Panel {
  private var bufferedImage:BufferedImage = null

  def imagePath(bufferedImageInsert: BufferedImage)
  {
    bufferedImage = bufferedImageInsert
  }

  override def paintComponent(g:Graphics2D) =
  {
    if (null != bufferedImage) g.drawImage(bufferedImage, 0, 0, null)
  }
}

object ImagePanel
{
  def apply() = new ImagePanel()
}
