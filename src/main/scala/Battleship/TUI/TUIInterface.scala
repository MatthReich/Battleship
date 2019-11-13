package Battleship.TUI

import Battleship.model.{Creator, Grid, Player}
import Battleship.TUI.TUIMethods._

object TUIInterface {
  def printGrid(grid: Grid, player: Player): Unit = {
    print(TUIMethods.printGrid(grid, player))
  }

  def printWelcome(creator_01: Creator, creator_02: Creator): Unit = {
    print(TUIMethods.printWelcome(creator_01, creator_02))
  }

  def printGetPlayer(player_01: Player, player_02: Player): Unit = {
    print(TUIMethods.printGetPlayer(player_01, player_02))
  }
}