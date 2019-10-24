package Battleship

object Battleship {
  def main(args: Array[String]): Unit = {

    val player_01 = model.Player("Marcel")
    val player_02 = model.Player("Matthias")

    print(
      "#" * 20 + "\n" +
        "Battleship \n\n" +
        "#" * 20 + "\n" +
        "created by: \n" + "" +
        "\t: " + player_01 + "\n" +
        "\t: " + player_02 + "\n" +
        "#" * 20 + "\n"
    )
  }

}
