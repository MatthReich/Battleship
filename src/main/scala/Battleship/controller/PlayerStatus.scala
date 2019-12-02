package Battleship.controller

object PlayerStatus extends Enumeration {

  type PlayerStatus = Value
  val PLAYER_ONE, PLAYER_TWO = Value

  val map: Map[PlayerStatus, String] = Map[PlayerStatus, String](
    PLAYER_ONE -> "player_01's turn",
    PLAYER_TWO -> "player_02's turn"
  )

  def message(playerStatus: PlayerStatus): String = {
    map(playerStatus)
  }

}
