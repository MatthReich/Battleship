package Battleship.model.Person

case class Creator(var name: String) extends InterfacePerson {
  override def toString: String = name

  override def addName(name: String): Unit = this.name = name
}
