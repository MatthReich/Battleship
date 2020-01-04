package Battleship

import Battleship.controller.{Controller, InterfaceController}
import com.google.inject.AbstractModule
import com.google.inject.name.Names

class GameModule extends AbstractModule {

  val fieldSize = 10

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("FieldSize")).to(fieldSize)
    bind(classOf[InterfaceController]).to(classOf[Controller])
  }
}
