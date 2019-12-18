package Battleship.model.Person

case class Player(name: String) extends InterfacePerson {

  override def toString: String = name
}
