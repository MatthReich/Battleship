package Battleship

import Battleship.controller.{Controller, InterfaceController}
import Battleship.model.gridComponent.InterfaceGrid
import Battleship.model.gridComponent.advancedGrid.Grid
import com.google.inject.AbstractModule

class GameModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[InterfaceGrid]).to(classOf[Grid])
    bind(classOf[InterfaceController]).to(classOf[Controller])
  }
}
