package Battleship

import Battleship.controller.{Controller, InterfaceController}
import Battleship.model.Person.{InterfacePerson, Player}
import Battleship.model.fileIoComponent._
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.{StrategyCollide, StrategyCollideNormal}
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

class GameModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[StrategyCollide].to[StrategyCollideNormal]
    bind[InterfaceGrid].to[Grid]
    bind[InterfacePerson].to[Player]
    bind[InterfaceShip].to[Ship]
    bind[InterfaceController].to[Controller]
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }
}
