package Battleship.model.Person

case class Player(name1: String) {
  val name: String = name1

  override def toString: String = name1
}
