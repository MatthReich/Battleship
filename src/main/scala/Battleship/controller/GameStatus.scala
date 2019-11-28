package Battleship.controller

object GameStatus extends Enumeration {

  type GameStatus = Value
  val IDLE, SOLVED = Value

  val map: Map[GameStatus, String] = Map[GameStatus, String](
    IDLE -> "",
    SOLVED -> "Game successfully finished"
  )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
