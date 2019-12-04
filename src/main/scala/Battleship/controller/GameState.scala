package Battleship.controller

object GameState extends Enumeration {

  type GameState = Value
  val IDLE, SOLVED = Value

  val map: Map[GameState, String] = Map[GameState, String](
    IDLE -> "",
    SOLVED -> "Game successfully finished"
  )

  def message(gameStatus: GameState): String = {
    map(gameStatus)
  }

}
