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

    print(printWelcome())

    setPlayer()

    print(printGetPlayer())

    printSetField()

    val fieldP_01_Ships = PlayerField(player_01, fieldSize)
    val fieldP_02_Ships = PlayerField(player_02, fieldSize)

    askShips(fieldP_01_Ships, player_01)
    askShips(fieldP_02_Ships, player_02)

    val fieldP_01 = PlayerField(player_01, fieldSize)
    val fieldP_02 = PlayerField(player_02, fieldSize)

    // @TODO add color to Player itself
    printField(fieldP_01_Ships, 1)
    printField(fieldP_02_Ships, 2)
    printField(fieldP_01, 1)
    printField(fieldP_02, 2)


  }
}
