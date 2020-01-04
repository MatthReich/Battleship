package Battleship

import Battleship.controller.{Controller, InterfaceController}
import Battleship.model.Person.{InterfacePerson, Player}
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import Battleship.model.shipComponent.InterfaceShip
import Battleship.model.shipComponent.advancedShip.Ship
import Battleship.model.shipComponent.strategyCollide.{StrategyCollide, StrategyCollideNormal}
import com.google.inject.AbstractModule

class GameModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[StrategyCollide]).to(classOf[StrategyCollideNormal])
    bind(classOf[InterfaceGrid]).to(classOf[Grid])
    bind(classOf[InterfacePerson]).to(classOf[Player])
    bind(classOf[InterfaceShip]).to(classOf[Ship])
    bind(classOf[InterfaceController]).to(classOf[Controller])
  }
}
