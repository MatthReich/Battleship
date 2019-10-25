package Battleship

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
      "\tPlayer Two: " + player_02 + "\n"
    )

    val field = new model.fieldTest(player_01)
    field.insertMap("A0", 0,false)
    field.insertMap("A0", 1,true)
    field.insertMap("A1", 0,true)
    field.insertMap("A2", 0,false)
    field.getKey("A1")
    field.getKey("A2")
    field.print()
  }

}
