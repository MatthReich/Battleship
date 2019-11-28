package Battleship.controller

object GameStatus extends Enumeration {

  type GameStatus = Value
  val IDLE, SOLVED = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    SOLVED -> "Game successfully solved",
  )

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }


}
