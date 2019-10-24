package Battleship

object Battleship {
  def main(args: Array[String]): Unit = {

    val player_01 = model.Player("Marcel")
    val player_02 = model.Player("Matthias")

    val creator_01 = model.Creator("Marcel")
    val creator_02 = model.Creator("Matthias")

    print(
      "#" * 20 + "\n" +
        "Battleship \n\n" +
        "Player One: " + player_01 + "\n" +
        "Player One: " + player_02 + "\n" +
        "#" * 20 + "\n" +
        "created by: \n" + "" +
        "\t: " + creator_01 + "\n" +
        "\t: " + creator_02 + "\n" +
        "#" * 20 + "\n"
    )
  }

}
