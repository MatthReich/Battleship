package Battleship.model.Person.persons

import Battleship.model.Person.InterfacePerson

case class Creator() extends InterfacePerson {
  var name: String = ""

  override def toString: String = name

  override def addName(name: String): Unit = this.name = name
}
