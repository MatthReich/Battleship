package Battleship.TUI

import Battleship.model.{Creator, Player, PlayerField}
import Battleship.TUI.TUIMethods._

object TUIInterface {
  val creator_01: Creator = Creator("Marcel")
  val creator_02: Creator = Creator("Matthias")
  var player_01 = Player("")
  var player_02 = Player("")
  var fieldSize: Int = 0


  def execute (): Unit = {

    printWelcome()

    setPlayer()

    printGetPlayer()

    printSetField()

    val fieldP_01 = PlayerField(player_01, fieldSize)
    val fieldP_02 = PlayerField(player_02, fieldSize)

    printField(fieldP_01, 1)
    printField(fieldP_02, 2)

  }
}
