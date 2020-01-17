package Battleship.model.fileIoComponent.fileIoXmlImpl

import Battleship.controller.GameState.GameState
import Battleship.controller.PlayerState.PlayerState
import Battleship.model.Person.InterfacePerson
import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.shipComponent.InterfaceShip
import com.google.inject.Inject

class FileIO @Inject()(var player: InterfacePerson, var player2: InterfacePerson
                      ) extends FileIOInterface {

  override def load: (InterfaceGrid, InterfaceGrid, InterfacePerson, InterfacePerson, Array[Int], Array[Int], InterfaceShip, Array[Int], Boolean, Boolean, String, GameState, PlayerState) = ???

  override def save(grid1: InterfaceGrid, grid2: InterfaceGrid, player: InterfacePerson, player2: InterfacePerson, shipSetting: Array[Int], shipSetting2: Array[Int], ship: InterfaceShip, shipCoordsSetting: Array[Int], shipSet: Boolean, shipDelete: Boolean, lastGuess: String, gameState: GameState, playerState: PlayerState): Unit = ???
}
