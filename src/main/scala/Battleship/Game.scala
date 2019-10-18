package Battleship


object Battleship {
  def main(args: Array[String]): Unit = {

    val player1 = model.Player("Marcel")
    val player2 = model.Player("Matthias")

    print(
      "#"*20 + "\n" +
      "Battleship \n\n" +
      "#"*20 + "\n" +
      "played by: \n" + "" +
      "\t: " + player1 + "\n" +
      "\t: " + player2 + "\n" +
      "#"*20 + "\n"
    )
  }

}
