package Battleship

import Battleship.aview.GUI.startGUI
import Battleship.aview.TUI.{TUIInterface, Tui}
import Battleship.controller.{GameState, InterfaceController, PlayerState}
import com.google.inject.Guice


object Game {
  val injector = Guice.createInjector(new GameModule)
  val controller = injector.getInstance(classOf[InterfaceController]) //new Controller(Grid(fieldSize), Grid(fieldSize))
  controller.init()
  val tui = new Tui(controller)
  val tuii = new TUIInterface(controller)
  val gui = new startGUI(controller)

  def main(args: Array[String]): Unit = {

    gui.visible = true

    var input: String = ""

    tuii.printWelcomeX()

    do {
      controller.getGameState match {
        case GameState.PLAYERSETTING => {
          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => print(tuii.printSetPlayer(1))
            case PlayerState.PLAYER_TWO => print(tuii.printSetPlayer(2))
          }
        }
        case GameState.SHIPSETTING => {
          // @TODO "2 0 3 0" geht nicht nach "0 0 0 1"
          // @TODO nach beenden setzen, gui keine änderung erst nach 1 setzen (nicht 1 enter)
          // @TODO nach state change noch falsches bild

          controller.getPlayerState match {
            case PlayerState.PLAYER_ONE => {
              tui.printGrid(0)
              tui.printShipSetSettings(controller.getNrPlayer1())
            }
            case PlayerState.PLAYER_TWO => {
              tui.printGrid(1)
              tui.printShipSetSettings(controller.getNrPlayer2())
            }
          }
        }
        case GameState.IDLE => {
          // @TODO player_two wird übersprungen
          // @TODO gui zeigt richtig an, aber fehler oben - erst bei enter wieder zurück gesetzt

          tui.printFirstTimeProcessLine()
        }
        case GameState.SOLVED => {}
      }

      input = scala.io.StdIn.readLine().toString
      tui.processLine(input)

      if ((controller.getNrPlayer1()(0) + controller.getNrPlayer1()(1) + controller.getNrPlayer1()(2) +
        controller.getNrPlayer1()(3)) == 0) {
        controller.setPlayerState(PlayerState.PLAYER_TWO)
      }
      if (controller.getNrPlayer2()(0) + controller.getNrPlayer2()(1) + controller.getNrPlayer2()(2) +
        controller.getNrPlayer2()(3) == 0) {
        controller.setPlayerState(PlayerState.PLAYER_ONE)
        controller.setGameState(GameState.IDLE)
      }
      if (controller.getGameState == GameState.SOLVED) input = "q"
    } while (input != "q")

    sys.exit(0)
  }
}