package Battleship.model.Person

case class Creator(name: String) extends InterfacePerson {
  override def toString: String = name
}
