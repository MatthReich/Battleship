package Battleship.controller

object GameState extends Enumeration {

  type GameState = Value
  val PLAYERSETTING, SHIPSETTING, IDLE, SOLVED = Value

  val map: Map[GameState, String] = Map[GameState, String](
    PLAYERSETTING -> "players are set",
    SHIPSETTING -> "ships are set",
    IDLE -> "",
    SOLVED -> "Game successfully finished"
  )

  def message(gameStatus: GameState): String = {
    map(gameStatus)
  }

}
