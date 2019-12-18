package Battleship.model.Person

trait InterfacePerson {
  def toString: String

  def newCreator(name: String): Creator = {
    Creator(name)
  }

  def newPlayer(name: String): Creator = {
    Creator(name)
  }
}
