package Battleship

import Battleship.model.PlayingField

object Game {
  def main(args: Array[String]): Unit = {

    val creator_01 = model.Creator("Marcel")
    val creator_02 = model.Creator("Matthias")

    print(
      "#" * 20 + "\n" +
        "    Battleship \n" +
        "#" * 20 + "\n" +
        "created by: \n" + "" +
        "\t: " + creator_01 + "\n" +
        "\t: " + creator_02 + "\n" +
        "#" * 20 + "\n\n\n"
    )

    print(Console.GREEN + "Please insert playername for player_01\n")
    val player_01 = model.Player(scala.io.StdIn.readLine().toString)
    print(Console.CYAN + "Please insert playername for player_02\n" + Console.RESET)
    val player_02 = model.Player(scala.io.StdIn.readLine().toString)

    print(
      "Actual playerconfiguration: \n" +
      "\tPlayer One: " + player_01 + "\n" +
      "\tPlayer Two: " + player_02 + "\n\n"
    )

    print("Please insert fieldsize\n" +
          "options: insert 10 for [10 x 10] field\n"
    )
    val fieldSize: Int = scala.io.StdIn.readLine().toInt

    val fieldP_01 = PlayingField(player_01, fieldSize)
    val fieldP_02 = PlayingField(player_02, fieldSize)

    print("\n")

    val outputP_01 = fieldP_01.printField()
    print(outputP_01 + "\n")

    val outputP_02 = fieldP_02.printField()
    print(outputP_02 + "\n")

  }

}
